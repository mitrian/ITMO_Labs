from iter_method import *

print(" "*10+"Метод простой итерации")
print("-"*42)
try:
    user_matrix_iteration()
except PermissionError as e:
    print("Недостаочно прав")
except FileNotFoundError as e:
    print("Файла с таким именем не существует")
except EOFError as e:
    print("Принудительное завершение программы")
except KeyboardInterrupt as e:
    print("Обработка прерывания")
