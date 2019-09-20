package main

import (
	"fmt"
	"time"
)

func f(s string) {
	fmt.Println(s)
}

func main() {
	f("i'm sync")

	go f("i'm async")

	go func(s string) {
		fmt.Println(s)
	}("i'm async")

	time.Sleep(1*time.Second)
	fmt.Println("done!")
}
