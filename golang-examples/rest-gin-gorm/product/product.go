package product

import "github.com/jinzhu/gorm"

type Product struct {
	gorm.Model
	Code string
	Price uint
}
