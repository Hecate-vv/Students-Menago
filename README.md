
### **Co zawiera `README.md`?**
1. **Wymagania** – Lista narzędzi i środowisk potrzebnych do uruchomienia.
2. **Konfiguracja** – Jak skonfigurować bazę danych i zmieniać ustawienia.
3. **Instrukcja uruchomienia** – Kroki do uruchomienia projektu w IntelliJ IDEA.
4. **Funkcjonalności** – Krótki opis działania aplikacji.

---

## **Wymagania**

- **Java 8+**
- **SQLite 3**
- **IntelliJ IDEA** (opcjonalnie, ale zalecane)

---

## **Konfiguracja**
Plik bazy danych SQLite (`students.db`) zostanie utworzony automatycznie przy pierwszym uruchomieniu aplikacji.

Jeśli chcesz użyć innej bazy danych:
1. Otwórz klasę `DatabaseConnection`.
2. Zmień wartość zmiennej `DATABASE_URL`:
   ```java
   private static final String DATABASE_URL = "jdbc:sqlite:<ścieżka_do_twojej_bazy_danych>";

---

## **Instrukcja**

1. Sklonuj repozytorium:
git clone https://github.com/Hecate-vv/Students-Menago.git

2. Skonfiguruj projekt w IntelliJ IDEA:

- Otwórz projekt w IntelliJ.
- Upewnij się, że wszystkie zależności są poprawnie skonfigurowane.

3. Uruchamianie aplikacji:

- Znajdz klasę MainWindow w folderze GUI. Uruchom ją używając skrótu klawiszowego (Shift +F10).
 Aplikacja otworzy okno GUI do zarządzania studentami.

---

## **Funkcjonalności**

1. Dodawanie studenta:
    Wypełnij pola:
        Student ID
        Name
        Age
        Grade
    Kliknij przycisk Add Student.

2. Wyświetlanie studentów

    Kliknij przycisk Display All Students, aby zobaczyć listę wszystkich studentów w bazie.

3. Usuwanie studenta

    Wprowadź Student ID do pola Student ID. Kliknij przycisk Delete Selected Student.

4. Aktualizowanie danych studenta

    Wprowadź Student ID oraz nowe dane do pól. Kliknij przycisk Update Student.

5. Obliczanie średniej ocen

    Kliknij przycisk Calculate Average Grade.