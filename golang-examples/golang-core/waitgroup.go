package main

import (
	"fmt"
	"sync"
)

func f(s string, waitGroup *sync.WaitGroup) {
	defer waitGroup.Done()
	fmt.Println(s)
}

func main() {
	var waitGroup sync.WaitGroup 
	waitGroup.Add(1)
	go f("i'm async!", &waitGroup)

	waitGroup.Add(1)
	go func(s string) {
		defer waitGroup.Done()
		fmt.Println(s)
	}("i'm async too!")

	waitGroup.Wait()

	fmt.Println("done!")
}
