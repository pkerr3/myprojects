(define (make-stream n f)
  (define (next m)
    (cons m (lambda () (next (f m)))))
    (next n))
    
  (define head car)
    
  (define (tail stream)
    ((cdr stream)))
      
  (define (nth stream n)
    (if (= n 0) (head stream)
      (nth (tail stream) (- n 1))))
    
  (define even (make-stream 0 (lambda (n) (+ n 2))))
  
  (define test (make-stream 4 (lambda (hi) (* hi 3))))