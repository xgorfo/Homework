package ru.tbank.hse.fd.streamPractise.service;

import ru.tbank.hse.fd.streamPractise.model.Car;
import ru.tbank.hse.fd.streamPractise.model.CarInfo;
import ru.tbank.hse.fd.streamPractise.model.Owner;

import java.util.List;
import java.util.Comparator;
import java.util.stream.Collectors;

public class CarService {

    /**
     * Приходит список Car
     * Необходимо вернуть список строк из Condition
     */
    public List<String> getConditions(List<Car> cars) {
        return cars.stream()
                .map(Car::getCondition)
                .collect(Collectors.toList());
    }

    /**
     * Приходит список Car
     * Необходимо вернуть только те, у которых Condition - "NEW"
     */
    public List<Car> getNewCars(List<Car> cars) {
        return cars.stream()
                .filter(car -> "NEW".equals(car.getCondition()))
                .collect(Collectors.toList());
    }

    /**
     * Приходит список Car
     * Необходимо вернуть количество Car, у которых больше 2 Owners
     */
    public long countCarsOwners(List<Car> cars) {
        return cars.stream()
                .filter(car -> car.getOwners().size() > 2)
                .count();
    }

    /**
     * Приходит список Car
     * Необходимо каждому элементу списка в поле age прибавить 1
     */
    public List<Car> incrementCarAge(List<Car> cars) {
        return cars.stream()
                .map(car -> {
                    car.setAge(car.getAge() + 1);
                    return car;
                })
                .collect(Collectors.toList());
    }

    /**
     * Приходит список Car
     * Необходимо вернуть Car, у которого самое большое значение age
     */
    public Car getOldestCar(List<Car> cars) {
        return cars.stream()
                .max(Comparator.comparingInt(Car::getAge))
                .orElse(null);
    }

    /**
     * Приходит список Car
     * Необходимо вернуть список имён всех владельцев
     * Имена не должны повторяться
     */
    public List<String> getOwnersCarsNames(List<Car> cars) {
        return cars.stream()
                .flatMap(car -> car.getOwners().stream())
                .map(Owner::getName)
                .distinct()
                .collect(Collectors.toList());
    }

    /**
     * Приходит список Car
     * Необходимо преобразовать его в список CarInfo
     */
    public List<CarInfo> mapToCarInfo(List<Car> cars) {
        return cars.stream()
                .map(car -> new CarInfo(car.getId(), car.getCondition(), car.getAge()))
                .collect(Collectors.toList());
    }

    /**
     * Приходит список Car
     * Необходимо вернуть не более двух машин, у которых Condition - BROKEN
     */
    public List<Car> getTwoBrokenCar(List<Car> cars) {
        return cars.stream()
                .filter(car -> "BROKEN".equals(car.getCondition()))
                .limit(2)
                .collect(Collectors.toList());
    }

    /**
     * Приходит список Car
     * Необходимо вернуть отсортированный по полю age список Car
     */
    public List<Car> getSortedCarsByAge(List<Car> cars) {
        return cars.stream()
                .sorted(Comparator.comparingInt(Car::getAge))
                .collect(Collectors.toList());
    }

    /**
     * Приходит список Car
     * Необходимо посчитать средний возраст всех машин
     */
    public double getAvgCarsAge(List<Car> cars) {
        return cars.stream()
                .mapToInt(Car::getAge)
                .average()
                .orElse(0.0);
    }

    /**
     * Приходит список Car
     * Проверить, что все машины с Condition - "Broken" старше 10 лет
     */
    public Boolean checkBrokenCarsAge(List<Car> cars) {
        return cars.stream()
                .filter(car -> "BROKEN".equals(car.getCondition()))
                .allMatch(car -> car.getAge() > 10);
    }

    /**
     * Приходит список Car
     * Проверить, что хотя бы у одной машины с Condition - "USED" был владелец по имени Adam
     */
    public Boolean checkCarOwnerName(List<Car> cars) {
        return cars.stream()
                .filter(car -> "USED".equals(car.getCondition()))
                .flatMap(car -> car.getOwners().stream())
                .anyMatch(owner -> "Adam".equals(owner.getName()));
    }

    /**
     * Приходит список Car
     * Необходимо вернуть любого Owner старше 36 лет
     */
    public Owner getAnyOwner(List<Car> cars) {
        return cars.stream()
                .flatMap(car -> car.getOwners().stream())
                .filter(owner -> owner.getAge() > 36)
                .findFirst()
                .orElse(null);
    }
}
