package com.javarush.task.task24.task2407;

/*
В работе вам иногда будет нужно закастить класс к какому-нибудь интерфейсу (тут Sayable),
который не реализован в текущем классе
 */
public class Cat implements Pet {
    private String name;

    public Cat(String name) {
        this.name = name;
    }

    /**
     * Это - механизм адаптирования к другому интерфейсу - Sayable
     * Внутри метода toSayable создайте class CatPet, реализующий интерфейс Sayable
     * Логика метода say:
     * Если i <= 0, то вывести на экран, что кот спит. Пример, "Васька спит."'
     * Иначе вывести фразу: "имя_кота говорит мяу!". Пример для i=3, "Васька говорит мяяяу!"
     * <p/>
     * <b>Пример вывода:</b>
     * Мурзик спит.
     * Васька говорит мяяу!
     * Кошка говорит мяяяяяу!
     * Мышь пищит.
     * Томас говорит мяу!
     * <p/>
     *
     * @param i количество букв 'я' в слове мяу
     * @return экземпляр класса CatPet
     */
    @Override
    public Sayable toSayable(final int i) {
        class CatPet implements Sayable {
            @Override
            public String say() {
                if (i > 0) {
                    String yaa = "";
                    for (int ay = 0; ay < i; ay++) {
                        yaa += "я";
                    }
                    return name + " говорит м" + yaa + "у!";
/*                    StringBuilder builder = new StringBuilder("м");
                    for (int j = 0; j < i; j++) {
                        builder.append("я");
                    }
                    builder.append("y!");
                    return name + " говорит " + builder.toString();*/
                } else return name + " спит.";
            }
        }
        return new CatPet();
    }
}