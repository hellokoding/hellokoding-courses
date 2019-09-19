package main

import (
	"fmt"
	"time"
)

func f(s string) {
	fmt.Println(s)
}

func main() {
	f("Runing defined function on the main thread")

	go f("Runing defined function on another thread")

	go func(s string) {
		fmt.Println(s)
	}("Running anonymous function on another thread")

	time.Sleep(1*time.Second)
	fmt.Println("done!")
}
