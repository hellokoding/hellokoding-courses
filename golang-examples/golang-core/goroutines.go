package main

import (
	"fmt"
	"time"
)

func f(s string) {
	fmt.Println(s)
}

func main() {
	f("i'm sync!")

	go f("i'm async!")

	go func(s string) {
		fmt.Println(s)
	}("i'm async too!")

	time.Sleep(time.Second)
	fmt.Println("done!")
}
