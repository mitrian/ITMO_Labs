from util import *


def matrix_print(matrix, n, b):
    for i in range(n):
        iline = ''
        for j in matrix[i]:
            if j >= 0 and j < 10:
                iline = iline + '  ' + str(j) + '  '
            elif (j < 0 and j > -10) or (j >= 10):
                iline = iline + ' ' + str(j) + '  '
            else:
                iline = iline + str(j) + '  '
        if b[i] >= 0 and b[i] < 10:
            iline = iline + '  ' + str(b[i])
        elif (b[i] < 0 and b[i] > -10) or (b[i] >= 10):
            iline = iline + ' ' + str(b[i])
        else:
            iline = iline + str(b[i])
        print(iline)


def read_matrix_from_console() -> list:
    while True:
        try:
            n = int(input("Введите размер матрицы: ").strip())
            if n < 1:
                print("Размер матрицы должен быть больше 0")
            else:
                break
        except ValueError:
            print("Требуется ввести целое число!")

    matrix = [[0] * n for i in range(n)]
    b = [0] * n

    while True:
        answ = input("Вы хотите автоматически сгенерировать матрицу[y/n]: ").strip()
        if answ == "y":
            matrix, b = random_matrix(n)
            print('Исходные данные:')
            matrix_print(matrix, n, b)
            break
        if answ == "n":
            for i in range(n):
                while True:
                    try:
                        iline = list(map(float, input(f"Введите {i + 1}-ую строку коэффициентов: ").
                                        replace(",", ".").strip().split()))

                        if len(iline) == n+1:
                            break
                        else:
                            if n>4:
                                print(f"Должно быть {n+1} аргументов")
                            else:
                                print(f"Должно быть {n + 1} аргумента")
                    except ValueError:
                        print("Неправильный формат ввода")
                matrix[i] = iline[0:n]
                b[i] = iline[-1]
            break
    while True:
        try:
            first_approx = list(map(float, input("Введите начальное приближение: ").replace(",", ".").split()))
            if len(first_approx) == n:
                break
            else:
                if n > 4:
                    print(f"Должно быть {n} аргументов")
                else:
                    print(f"Должно быть {n} аргумента")
        except ValueError:
            print("Неправильный формат ввода")
    while True:
        try:
            eps = float(input("Введите ε: ").replace(",",".").strip())
            if eps <= 0:
                print("Значение ε должно быть больше 0")
            else:
                break
        except ValueError:
            print("Неправильный формат ввода")
    while True:
        try:
            max_iter = int(input("Введите максимальное число итераций: ").strip())
            if max_iter <= 0:
                print("Количество максимальных итераций должно быть целым и больше 0")
            else:
                break
        except ValueError:
            print("Неправильный формат ввода")
    return [n, matrix, b, first_approx, eps, max_iter]


def read_matrinx_from_file() -> list:
    filename = input("Введите имя файла: ").strip()
    file = open(filename, 'r')
    try:
        n = int(file.readline())
        if n < 1:
            print("Неправильный формат файла, должно быть хотя бы 1 эл-т")
            exit()
        matrix = [[0] * n for i in range(n)]
        b = [0] * n
        for i in range(n):
            line = list(map(float, file.readline().replace(",", ".").split()))
            if len(line) != n+1:
                print(f"Неправильный формат файла, недотсаточно эл-тов в строке {i+2}")
                file.close()
                exit()
            matrix[i] = line[0:n]
            b[i] = line[-1]
        first_approx = list(map(float, file.readline().replace(",", ".").split()))
        if len(first_approx) != n:
            print("Неправильный формат файла, проверьте начальные приближения")
            file.close()
            exit()
        eps = float(file.readline().replace(",", "."))
        if (eps <= 0):
            print("Неправильный формат файла, проверьте значение ε")
            file.close()
            exit()
        max_iter = int(file.readline())
        if (max_iter<= 0):
            print("Неправильный формат файла, проверьте количество максимальных итераций")
            file.close()
            exit()
    except ValueError:
        print("Неправильный формат файла, должны использоваться числа")
        file.close()
        exit()
    print("-" * 42)
    print("Исходные данные файла")
    print("Размер матрицы: "+str(n))
    matrix_print(matrix, n, b)
    first_aprox_line = ''
    for i in first_approx:
        first_aprox_line = first_aprox_line + ' ' + str(i)
    print("Начальное приближение: " + first_aprox_line)
    print("ε: " + str(eps))
    print("Максимальное число итераций: " + str(max_iter))
    print("Чтение файла завершено")
    return [n, matrix, b, first_approx, eps, max_iter]
