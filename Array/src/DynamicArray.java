import java.util.Arrays;

public class DynamicArray<T> {
    private Object[] array;//Массив для хранения данных
    private int size;//Текущий размер массива

    // Конструктор для инициализации
    public DynamicArray() {
        array = new Object[10];//Начальная умкость массива
        size = 0;//Количество элементов в массиве
    }

    // 1. Добавление элемента в конец
    public void add(T value) {
        ensureCapacity();//Проверяем нужно ли увеличить емкость массива
        array[size++] = value;//Добавляем элемент и увеличиваем размер
    }

    // 2. Добавление элемента по индексу
    public void add(int index, T value) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("Index out of bounds");
        }
        ensureCapacity();
        System.arraycopy(array, index, array, index + 1, size - index);
        array[index] = value;
        size++;
    }

    // 3. Получение размера массива
    public int size() {
        return size;
    }

    // 4. Получение элемента по индексу
    @SuppressWarnings("unchecked")
    public T get(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index out of bounds");
        }
        return (T) array[index];
    }

    // 5. Очистка массива
    public void clear() {
        array = new Object[10];
        size = 0;
    }

    // 6. Удаление элемента по значению
    public boolean remove(T value) {
        for (int i = 0; i < size; i++) {
            if (array[i].equals(value)) {
                remove(i);
                return true;
            }
        }
        return false;
    }

    // 7. Удаление элемента по индексу
    public void remove(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index out of bounds");
        }
        System.arraycopy(array, index + 1, array, index, size - index - 1);
        size--;
    }

    // Вспомогательный метод для увеличения емкости массива
    private void ensureCapacity() {
        if (size == array.length) {
            array = Arrays.copyOf(array, array.length * 2);
        }
    }

    // Печать всех элементов массива
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("[");
        for (int i = 0; i < size; i++) {
            sb.append(array[i]);
            if (i < size - 1) {
                sb.append(", ");
            }
        }
        sb.append("]");
        return sb.toString();
    }

    // Тестирование
    public static void main(String[] args) {
        DynamicArray<String> list = new DynamicArray<>();
        list.add("Farid");
        list.add("Dima");
        list.add("Dawa");
        System.out.println(list);

        list.add(1, "Inserted");
        System.out.println(list);

        list.remove("Dima");
        System.out.println(list);

        list.remove(0);
        System.out.println(list);

        System.out.println("Size: " + list.size());
        list.clear();
        System.out.println("Cleared: " + list);
    }
}