package main

import (
	"fmt"
	"strconv"
)

func main() {
	str := "1"

	n, err := strconv.Atoi(str)

	if err != nil {
		fmt.Println(err)
	} else {
		fmt.Println(n)
	}
}
