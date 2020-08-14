package main

import (
	"fmt"
	"net/http"
	"log"

	"golang.org/x/time/rate"
)

var limiter = rate.NewLimiter(2, 5)

func throttle(next http.Handler) http.Handler {
	return http.HandlerFunc(func(w http.ResponseWriter, r *http.Request) {
		if !limiter.Allow() {
			http.Error(w, http.StatusText(http.StatusTooManyRequests), http.StatusTooManyRequests)
			log.Fatalln(http.StatusText(http.StatusTooManyRequests))
			return
		}

		next.ServeHTTP(w, r)
	})
}

func handler(w http.ResponseWriter, r *http.Request) {
	fmt.Fprintf(w, "Hello, World!")
}

func main() {
	mux := http.NewServeMux()

	mux.HandleFunc("/", handler)
	
	err := http.ListenAndServe(":8080", throttle(mux))
	if err != nil {
		panic(err)
	}
}