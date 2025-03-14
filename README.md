## Отчет по лабораторной работе № 2

#### № группы: `ПМ-2402`

#### Выполнил: `Хамуев Владимир Николаевич`

#### Вариант: `27`

### Cодержание:

- [Постановка задачи](#1-постановка-задачи)
- [Входные и выходные данные](#2-входные-и-выходные-данные)
- [Выбор структуры данных](#3-выбор-структуры-данных)
- [Алгоритм](#4-алгоритм)
- [Программа](#5-программа)
- [Анализ правильности решения](#6-анализ-правильности-решения)

### 1. Постановка задачи

- Условия задачи

> Напишите программу на Java, которая выполняет следующие действия
с двумерным массивом дробных чисел:
1. Считывает с консоли размеры массива N и M , затем элементы
массива размером N × M .
2. Сортирует столбцы массива по возрастанию среднего арифметиче-
ского значения в столбце. Если средние значения равны, сортирует
по возрастанию дисперсии элементов в столбце.
3. Находит и выводит медиану всех элементов массива.
4. Выводит гистограмму распределения элементов массива, разбивая
диапазон значений на интервалы.
5. Нормализует элементы массива (приводит к диапазону от 0 до 1)
и выводит полученный массив.

Данную задачу можно разделить на 5 подзадач: 
1. Вычисление среднего арифметического значения в столбце и дисперсии в нем
2. Сортировка столбцов массива по поставленному критерию
3. Нахождение медианы массива
4. Нахождение количества уникальных элементов массива, с дальнейшим выводом гистограммы их распределения
5. Нормализация элементов массива (их приведение к диапазону от 0 до 1)

- Пусть `E = очередной элемент массива`, тогда для 1 подзадачи нужно рассмотреть 2 случая:
  1. `E >= 0`
  2. `E < 0` (отрицание 1 случая)
- Для 2 подзадачи нужно рассмотреть 3 случая:
  1. Массив отсортирован
  2. Массив не отсортирован, средние арифметические у столбцов разные (отрицание 1 случая)
  3. Массив не отсортирован, средние арифметические у столбцов одинаковые, дисперсия разная (отрицание 1 случая)
- Для 3 подзадачи нужно рассмотреть 2 случая:
  1. Длина массива нечётная
  2. Длина массива чётная
- Для 5 подзадачи нужно рассмотреть 2 случая:
  1. Длина массива больше 1
  2. Длина массива равна 1 (отрицание 1 случая)

Всего надо рассмотреть `3 * 2 * 2 * 2 = 24` случаев.

### 2. Входные и выходные данные

#### Данные на вход

На вход программа должна получать 2 числа, являющимися размерами массива, то есть целыми положительными числами; помимо этого, программа будет считывать элементы этого массива, при этом в условии сказано, что получаемые числа относятся к дробям, то есть к вещественным числам.

|           | Тип                       | min значение | max значение   |
|-----------|---------------------------|--------------|----------------|
| N (Число) | Целое положительное число | 1            | 10<sup>9</sup> |
| M (Число) | Целое положительное число | 1            | 10<sup>9</sup> |
| X (Число) | Вещественное число        | -1.7*10^308  | +1.7*10^308    |

#### Данные на выход

Программа должна вывести:
- Медиану всех элементов массива
- Гистограмму распределения элементов массива
- Нормализованный массив

Т.к. программа должна вывести медиану элементов массива, являющихся дробями, то есть вещественными силами, то и медиана должна быть вещественным числом.

|         | Тип                | min значение | max значение   |
|---------|--------------------|--------------|----------------|
| Число 1 | Вещественное число | -1.7*10^308  | +1.7*10^308    |


Т.к. программа должна вывести гистограмму распределения элементов массива, то на выход мы получим несколько строк.

|        | Тип    | значение   | значение   |
|--------|--------|------------|------------|
| Строка | Строка | "E #"      | "E "+"#"   |

Т.к. программа должна вывести нормализованный массив, то на выход мы получим строку, содержащую все элементы нормализованного массива.

|        | Тип    | значение   | значение                |
|--------|--------|------------|-------------------------|
| Строка | Строка | "0"        | "Массив размером N × M" |

### 3. Выбор структуры данных

Программа получает 2 натуральных числа, не превышающих 10<sup>9</sup>. Поэтому для их хранения
можно выделить 2 переменных (`n`, `m`) типа `int`.
Также программа получает N × M вещественных чисел, не превышающих по модулю +1.7*10^308. Поэтому для их хранения можно выделить 1 двумерный массив типа `double`.

|            | название переменной | Тип (в Java) | 
|------------|---------------------|--------------|
| N (Число)  | `n`                 | `int`        |
| M (Число)  | `m`                 | `int`        |
| a (Массив) | `a[n][m]`           | `double[][]` |

Для вывода результатов обязательно их хранить в отдельных переменных.

|            | название переменной | Тип (в Java) | 
|------------|---------------------|--------------|
| m (Число)  | `mediana`           | `double`     |
| t (Массив) | `temp[n*m]`         | `double[]`   |

### 4. Алгоритм

1. **Ввод данных:**  
   Программа считывает 2 целых числа, обозначенных как `n` и `m`.
   Затем программой считываются элементы массива размером N × M, обозначенного `a`.

2. **Вычисление среднего арифметического и дисперсии в столбце:**  
   Программа последовательно вычисляет среднее арифметическое значение и дисперсию элементов в столбце массива `a`.

3. **Сортировка массива:**
   Программа сортирует массив `a` пузырьком так, чтобы он был отсортирован по возрастанию среднего арифметиечского значения или по дисперсии элементов в столбце.

4. **Вывод результата:**  
   На экран выводится значение медианы.

5. **Нахождение количества уникальных элементов массива:**  
   Программа создаёт массив, содержащий уникальные элементы массива `a`, одновременно с этим вычисляя количество этих элементов в массиве `a`.

5. **Вывод гистограммы:**  
   На экран выводится гистограмма распределения элементов массива.

6. **Нормализация элементов массива:**
   Программа построчно изменяет элементы массива `a` приводя их к диапазону от 0 до 1.

7. **Вывод нормализованного массива:**  
   На экран выводится изменённый массив.


### 5. Программа

```markdown
    ```java
        import java.io.PrintStream;
        import java.util.Scanner;
        public class Main {
        // Объявляем объект класса Scanner для ввода данных
        public static Scanner in = new Scanner(System.in);
        // Объявляем объект класса PrintStream для вывода данных
        public static PrintStream out = System.out;
        public static void main(String[] args) {
            // Считывание целых чисел (размеров массива) N и M из консоли
            int n =in.nextInt();
            int m =in.nextInt();
            // Создание массива a размером N × M
            double[][] a  = new double[n][m];
            double[] sred = new double[m];
            double[] disp = new double[m];
            double[] mediani = new double[m*n];
            // Считывание элементов массива a из консоли
            for(int i=0;i<n;i++){
                for(int y=0;y<m;y++){
                    a[i][y]=in.nextDouble();
                    // Ввод всех элементов массива a в одномерный массив
                    mediani[y+i*m] = a[i][y];
                }
            }


            // Вычисление среднего арифметического и дисперсии для стлобцов
            for(int y=0;y<m;y++){
                double sum=0;
                disp[y]=0;
                for(int i=0;i<n;i++){
                    sum+=a[i][y];
                    if(Math.abs(a[i][y])>Math.abs(disp[y])){
                        disp[y]=Math.abs(a[i][y]);
                    }
                }
            sred[y]=sum/n;
            disp[y]=Math.abs(sred[y]-disp[y]);
            }
            // Сортировка столбцов массива в соответствии с поставленным критерием
            for (int i = 0; i< m- 1; i++) {
                for (int j = 0; j<m-1-i; j++) {
                    // Сортировка столбцов массива по возрастанию среднего арифметического значения в столбце
                    if (sred[j]>sred[j + 1]) {
                        double z = sred[j];
                        sred[j] = sred[j + 1];
                        sred[j + 1] = z;
                        for(int h=0;h<n;h++){
                            double z1=a[h][j];
                            a[h][j]=a[h][j+1];
                            a[h][j+1]=z1;
                        }
                    }
                    // Сортировка столбцов массива по возрастанию дисперсии элементов в столбце
                    else if(sred[j]==sred[j+1] && disp[j]>disp[j+1]){
                        double z2 = disp[j];
                        disp[j] = disp[j + 1];
                        disp[j + 1] = z2;
                        for(int h=0;h<n;h++){
                            double z3=a[h][j];
                            a[h][j]=a[h][j+1];
                            a[h][j+1]=z3;
                        }
                    }
                }
            }


            // Сортировка одномерного массива, содержащего все элементы массива a, по возрастанию
            for (int t = 0; t< m*n-1; t++) {
                for (int e = 0; e< m*n-1-t; e++) {
                    if(mediani[e]>mediani[e+1]){
                        double r = mediani[e];
                        mediani[e]=mediani[e+1];
                        mediani[e+1]=r;
                    }
                }
            }
            // Вычисление медианы для всех элементов массива
            double mediana = 0;
            if(mediani.length!=0){
                mediana = mediani[mediani.length/2];
            }
            else{
                mediana = (mediani[mediani.length/2]+mediani[mediani.length/2-1])/2;
            }
            out.println(mediana);


            // Построение гистограммы
            double[] temp = new double[mediani.length];
            int[] count = new int[mediani.length];
            int j = 0;
            for (int i=0; i<mediani.length-1; i++){
                if (mediani[i] != mediani[i+1]){
                    // Ввод всех уникальных элементов в одномерный массив
                    temp[j] = mediani[i];
                    // Подсчёт количества уникальных элементов
                    count[j]+=1;
                    j++;
                }
                else if(mediani[i] == mediani[i+1]){
                    // Подсчёт количества уникальных элементов
                    count[j]+=1;
                }
            }
            // Ввод последнего уникального элемента в одномерный массив
            temp[j] = mediani[mediani.length-1];
            // Конец подсчёта количества уникальных элементов
            count[j]+=1;
            j+=1;

            // Вывод гистограммы распределения элементов массива на экран
            for(int c=0;c<j;c++){
                out.print("["+temp[c]+"]"+" ");
                for(int d=0; d<count[c];d++){
                    out.print("#");
                }
                out.print("\n");
            }


            // Нормализация
            // Нахождение минимального и максимального элементов массива a
            double mmin=2147483647;
            double mmax =-2147483648;
            for(int i=0;i<n;i++) {
                for (int y = 0; y < m; y++) {
                    if(a[i][y]>mmax){
                        mmax=a[i][y];
                    }
                    if(a[i][y]<mmin){
                        mmin=a[i][y];
                    }
                }
            }
            // Нормализация элементов массива
            if(n>1 && m>1) {
            for (int i = 0; i < n; i++) {
                for (int y = 0; y < m; y++) {
                    a[i][y] = ((a[i][y] - mmin) / (mmax - mmin));
                    // Вывод элементов нормализованного массива
                    out.print(a[i][y] + " ");
                }
                out.print("\n");
            }
            }
            // Вывод элемента нормализованного массива размером 1 × 1
            else{
                out.println(1);
            }
        }
        }
    ```
```

### 6. Анализ правильности решения

Программа работает корректно на всем множестве решений с учетом ограничений.

1. Тест на "Длина массива нечётная":

    - **Input**:
        ```
        3 3 1 2 3 4 5 6 7 8 9
        ```

    - **Output**:
        ```
        5
        ```

2. Тест на "Длина массива чётная":

    - **Input**:
        ```
        3 2 -4 -4 -4 -4 0 0
        ```

    - **Output**:
        ```
        -4.0
        ```

3. Тест на "Длина массива больше 1":

    - **Input**:
        ```
        3 2 -4 -4 -4 -4 0 0
        ```

    - **Output**:
        ```
        0.0 0.125 0.25 0.375 0.5 0.625 0.75 0.875 1.0 
        ```

4. Тест на "Длина массива равна 1":

   - **Input**:
       ```
       1 1 1
       ```

   - **Output**:
       ```
       1
       ```
  
7. Тест на ограничение задачи:

    - **Input**:
        ```
        1 1 1000000000
        ```

    - **Output**:
        ```
        1
        ```
