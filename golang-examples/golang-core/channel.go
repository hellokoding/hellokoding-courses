package main

import (
	"fmt"
)

func f(c chan string) {
	c <- "hello, channel!"
}

func main() {
	c := make(chan string)
	
	go f(c)
	s := <- c

	fmt.Println(s)
}