package main

import (
	"fmt"
	"sync"
)

func f(s string, wg *sync.WaitGroup) {
	defer wg.Done()
	fmt.Println(s)
}

func main() {
	var wg sync.WaitGroup 
	wg.Add(1)
	go f("i'm async!", &wg)

	wg.Add(1)
	go func(s string) {
		defer wg.Done()
		fmt.Println(s)
	}("i'm async too!")

	wg.Wait()

	fmt.Println("done!")
}
