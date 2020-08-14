package main

import (
	"net/http"
)

var sem = make(chan int, 100)

func Serve(queue chan *Request) {
	for req := range queue {
		sem <- 1
		go func(req *http.Request) {
			process(req)
			<- sem
		}(req)
	}

	http.HandleFunc()
}

