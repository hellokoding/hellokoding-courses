package main

import (
	"fmt"
	"strconv"
)

func main() {
	str := "1001"

	n, err := strconv.ParseInt(str, 2, 32)

	if err != nil {
		fmt.Println(err)
	} else {
		fmt.Println(n)
	}
}
