from read_util import *


def check_diagonal_element_dominance(matrix, n) -> bool:
    strictly_bigger = False
    not_bigger = True
    for i in range(n):
        delta = sum(map(abs, matrix[i])) - abs(matrix[i][i])
        if abs(matrix[i][i]) < delta:
            not_bigger = False
        if matrix[i][i] > delta:
            strictly_bigger = True
    return strictly_bigger and not_bigger


# def reorganize_matrix(matrix, b, n) -> list:
#     possibility_flag = True
#     line_swap = False
#     for i in range(n):
#         current_line = matrix[i].copy()
#         delta = sum(map(abs, matrix[i])) - abs(matrix[i][i])
#         if abs(matrix[i][i]) < delta:
#             for j in range(n):
#                 delta = sum(map(abs, matrix[j])) - abs(matrix[j][i])
#                 if abs(matrix[j][i]) >= delta:
#                     # matrix[i] = matrix[j].copy()
#                     matrix[i] = matrix[j]
#                     matrix[j] = current_line
#                     b[i], b[j] = b[j], b[i]
#                     line_swap = True
#                     break
#                 if j == n - 1:
#                     if (abs(matrix[j][i]) < delta) and possibility_flag:
#                         possibility_flag = False
#     if possibility_flag:
#         if not check_diagonal_element_dominance(matrix, n):
#             print('Преобладание диагональных элементов недостижимо')
#             return matrix
#     if line_swap:
#         print("Матрица изменена:")
#         matrix_print(matrix, n, b)
#         return matrix
#     return matrix
def reorganize_matrix(matrix, b, n) -> list:
    possibility_flag = True
    line_swap = False
    for i in range(n):
        current_line = matrix[i].copy()
        delta = sum(map(abs, matrix[i])) - abs(matrix[i][i])
        if abs(matrix[i][i]) < delta:
            for j in range(i+1,n):
                delta = sum(map(abs, matrix[j])) - abs(matrix[j][i])
                if abs(matrix[j][i]) >= delta:
                    matrix[i] = matrix[j]
                    matrix[j] = current_line
                    b[i], b[j] = b[j], b[i]
                    line_swap = True
                    break
                if j == n - 1:
                    if (abs(matrix[j][i]) < delta) and possibility_flag:
                        possibility_flag = False
    if possibility_flag:
        if (not check_diagonal_element_dominance(matrix, n)) and (len(matrix) >1):
            print('Преобладание диагональных элементов недостижимо')
            return matrix
    if line_swap:
        print("Матрица изменена:")
        matrix_print(matrix, n, b)
        return matrix
    return matrix


def iterate_method(c_matrix, n, d, first_approx, eps, max_iter) -> list:
    counter = 0
    answer = first_approx
    diff = [0] * n
    while counter < max_iter:
        ans_matrix = [0] * n
        for i in range(n):
            summ = 0
            for j in range(n):
                summ += answer[j] * c_matrix[i][j]
            ans_matrix[i] = summ + d[i]
            diff[i] = ans_matrix[i] - answer[i]
        answer = ans_matrix
        max_diff = max(map(abs, diff))
        if max_diff < eps:
            break
        counter += 1
    return [answer, counter]


def user_matrix_iteration():
    while 1:
        inp_type = input("Использовать для ввода данных[console/file]: ").strip()
        if inp_type == "console":
            n, matrix, b, first_approx, eps, max_iter = read_matrix_from_console()
            break
        elif inp_type == "file":
            n, matrix, b, first_approx, eps, max_iter = read_matrinx_from_file()
            break
    print("-" * 42)
    print('Получены все необходимые данные')
    matrix = reorganize_matrix(matrix, b, n)
    c = [[0] * n for i in range(n)]
    d = [0] * n
    for i in range(n):
        if matrix[i][i] == 0:
            print("Метод не работает, тк диагональный эл-т равен 0")
            exit()
        for j in range(n):
            if i != j:
                c[i][j] = - matrix[i][j] / matrix[i][i]
            else:
                c[i][j] = 0
        d[i] = b[i] / matrix[i][i]

    answer, counter = iterate_method(c, n, d, first_approx, eps, max_iter)
    print("-" * 42)
    #print(f"Число итераций: {counter}")
    print("Приближенное решение:")
    for i in range(len(answer)):
        print(f"x{i + 1}: " + str(answer[i]))
