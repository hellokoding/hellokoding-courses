package main

import (
	"fmt"
	"strconv"
)

func main() {
	var i int64 = 9

	s := strconv.FormatInt(i, 2)

	fmt.Println(s)
}
