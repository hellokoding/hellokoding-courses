package main

import (
	"sync"
	"time"
	"fmt"
)

type MyWaitGroup struct {
	sync.WaitGroup
}

func (wg *MyWaitGroup) waitTimeout(timeout time.Duration) bool {
	done := make(chan struct{})
	
    go func() {
        defer close(done)
        wg.Wait()
	}()
	
    select {
    case <-done:
        return false
    case <-time.After(timeout):
        return true
    }
}

func f(s string, waitGroup *MyWaitGroup) {
	defer waitGroup.Done()
	fmt.Println(s)
}

func main() {
	var waitGroup MyWaitGroup 
	waitGroup.Add(1)
	go f("i'm async!", &waitGroup)

	waitGroup.Add(1)
	go func(s string) {
		defer waitGroup.Done()
		time.Sleep(2*time.Second)
		fmt.Println(s)
	}("i'm async too!")

	r := waitGroup.waitTimeout(time.Second)
	if r == true {
		fmt.Println("Wait timeout!")
	} else {
		fmt.Println("done!")
	}
}