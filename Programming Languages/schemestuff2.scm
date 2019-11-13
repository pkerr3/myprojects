(define (counter in arr)
  (cond
    ((null? in) 0)
    ((not (null? arr))
    (cond
      ((eqv? in (car arr))
        (+ 1 (counter in (cdr arr))))
      (else (counter in (cdr arr)))))
    (else + 0)))