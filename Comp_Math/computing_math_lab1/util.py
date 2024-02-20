from random import randint


def random_matrix(size: int) -> list:
    matrix = [[0] * size for i in range(size)]
    b = [0] * size
    for i in range(size):
        b[i] = randint(-20, 20)
        for j in range(size):
            matrix[i][j] = randint(-20, 20)
    return [matrix, b]
