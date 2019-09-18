package product

import (
	"strconv"
	"log"
	"net/http"
	"github.com/gin-gonic/gin"
)

type ProductAPI struct {
	ProductService ProductService
}

func ProvideProductAPI(p ProductService) ProductAPI {
	return ProductAPI{ProductService: p}
}

func (p *ProductAPI) FindAll(c *gin.Context) {
	products := p.ProductService.FindAll()

	c.JSON(http.StatusOK, gin.H{"products": ToProductDTOs(products)})
}

func (p *ProductAPI) FindByID(c *gin.Context) {
	id, _ :=  strconv.Atoi(c.Param("id"))
	product := p.ProductService.FindByID(uint(id))
	
	c.JSON(http.StatusOK, gin.H{"product": ToProductDTO(product)})
}

func (p *ProductAPI) Create(c *gin.Context) {
	var productDTO ProductDTO
	err := c.BindJSON(&productDTO)
	if err != nil {
		log.Fatalln(err)
		c.Status(http.StatusBadRequest)
		return
	}

	createdProduct := p.ProductService.Save(ToProduct(productDTO))

	c.JSON(http.StatusOK, gin.H{"product": ToProductDTO(createdProduct)})
}

func (p *ProductAPI) Update(c *gin.Context) {
	var productDTO ProductDTO
	err := c.BindJSON(&productDTO)
	if err != nil {
		log.Fatalln(err)
		c.Status(http.StatusBadRequest)
		return
	}

	id, _ :=  strconv.Atoi(c.Param("id"))
	product := p.ProductService.FindByID(uint(id))
	if product == (Product{}) {
		c.Status(http.StatusBadRequest)
		return
	}

	product.Code = productDTO.Code
	product.Price = productDTO.Price
	p.ProductService.Save(product)

	c.Status(http.StatusOK)
}

func (p *ProductAPI) Delete(c *gin.Context) {
	id, _ :=  strconv.Atoi(c.Param("id"))
	product := p.ProductService.FindByID(uint(id))
	if product == (Product{}) {
		c.Status(http.StatusBadRequest)
		return
	}

	p.ProductService.Delete(product)

	c.Status(http.StatusOK)
}
