package main

import (
	"github.com/jinzhu/gorm"
	"github.com/google/wire"
	"rest-gin-gorm/product"
)

func initProductAPI(db *gorm.DB) product.ProductAPI {
	wire.Build(product.ProvideProductRepostiory, product.ProvideProductService, product.ProvideProductAPI)

	return product.ProductAPI{}
}