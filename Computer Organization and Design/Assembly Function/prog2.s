.globl prog2

# Phillip Allen Kerr Jr.
# 89-046-5218

prog2:
	# initialization
	pushl %ebp # store %esp in %ebp
	movl %esp, %ebp # initialize new %esp to %ebp
	pushl %ebx # store %esp in %ebx

	# doing j - i
	movl 12(%ebp), %eax # store "j" in %eax
	movl 8(%ebp), %ecx # store "i" in %ecx
	subl %ecx, %eax # subtract %ecx ("i") from %eax ("j")

	# doing *k * (*k)
	movl 16(%ebp), %ebx # move pointer to k to %ebx
	movl (%ebx), %edx # put k in %edx register
	sall $2, %edx # shift k to the left
	addl (%ebx), %edx # adds one more k to what is now k*4
	movl %edx, (%ebx) # put k in the pointer value

	# the array problem
	movl 20(%ebp), %edx # set array address to %edx
	movl 24(%ebp), %ecx # set l address to %ecx
	movl $0, %edi # put 0 in %edi so it is ready to handle values
	addl (%edx), %edi # put the first value (a[0]) in %edi
	addl $4, %edx # reads next array element; integers are 16 bits, so 4 bytes, hence the offset
					# of 4
	addl (%edx), %edi # adds next array element (a[1]) into the sum address (%edi)
	addl $4, %edx # read next array element by the afforementioned reasoning
	addl (%edx), %edi # adds a[2] to %edi
	addl $4, %edx # reads next array element
	addl (%edx), %edi # adds a[3] to %edi
	addl $4, %edx # reads next array element
	addl (%edx), %edi # adds a[4] to %edi
	movl %edi, (%ecx) # sets the sum address (%edi) to the l pointer ((%ecx))

	# the end
	popl %ebx # fixes original %esp
	popl %ebp # return initial base pointer
	ret # return
	
