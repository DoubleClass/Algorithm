
def three_sum(numbers,target):
    for i in range(len(numbers)):
        for j in range(i+i,len(numbers)):
            for k in range(j+1,len(numbers)):
                if numbers[i]+numbers[j]+numbers[k]==target:
                    return (numbers[i],numbers[j],numbers[k])
    return None
test_case=(([19,3,7,10,11],18,None),
           ([19,3,7,10,11],20,(3,7,10)),
           ([4,5,6],12,None))
for numbers, target ,expected in test_case:
    print("Input: %r %d Expect:%r Got:%r"%(numbers,target,expected,
                                           three_sum(numbers,target)))


def find3Numbers(A, arr_size, sum):
    for i in range(0, arr_size - 1):
        # Find pair in subarray A[i + 1..n-1]
        # with sum equal to sum - A[i]
        s = set()
        curr_sum = sum - A[i]
        for j in range(i + 1, arr_size):
            if (curr_sum - A[j]) in s:
                print("Triplet is", A[i],
                      ", ", A[j], ", ", curr_sum - A[j])
                return True
            s.add(A[j])

    return False

# Driver program to test above function
A = [1, 4, 45, 6, 10, 8]
sum = 22
arr_size = len(A)
find3Numbers(A, arr_size, sum)
