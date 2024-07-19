/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package uas.asia.noteapp;

import java.util.List;
import java.util.Scanner;

/**
 *
 * @author budi
 */
public class NoteAppMenu {
    private NoteService noteService;
    private Scanner scanner;

    public NoteAppMenu(String databasePath) {
        noteService = new NoteService(new DatabaseStorage(databasePath));
        scanner = new Scanner(System.in);
    }

    public void start() {
        int choice;
        do {
            printMenu();
            choice = scanner.nextInt();
            scanner.nextLine();  // Consume newline
            switch (choice) {
                case 1:
                    addNote();
                    break;
                case 2:
                    showNotes();
                    break;
                case 3:
                    deleteNote();
                    break;
                case 4:
                    System.out.println("Exiting...");
                    break;
                default:
                    System.out.println("Pilihan Tidak Valid. Silahkan & Pastikan Memilih Opsi (1-4).");
            }
        } while (choice != 4);
    }

    private void printMenu() {
        System.out.println("\nNote App Menu: Oleh (Wahyu Agung Pramono Alfani) NIM (23201075).");
        System.out.println("1. Add note");
        System.out.println("2. Show notes");
        System.out.println("3. Delete note");
        System.out.println("4. Exit");
        System.out.print("Choose an option: ");
    }

    private void addNote() {
        System.out.print("Enter note: ");
        String note = scanner.nextLine();
        noteService.createNote(note);
        System.out.println("Note disimpan: " + note);
    }

    private void showNotes() {
        List<String> notes = noteService.readNotes();
        System.out.println("Note tersimpan:");
        if (notes.isEmpty()) {
            System.out.println("No notes found.");
        } else {
            for (int i = 0; i < notes.size(); i++) {
                System.out.println((i + 1) + ". " + notes.get(i));
            }
        }
    }

    private void deleteNote() {
    showNotes();
    List<String> notes = noteService.readNotes();
    if (!notes.isEmpty()) {
        System.out.print("Enter the number of the note to delete: ");
        int noteIndex = scanner.nextInt() - 1;
        scanner.nextLine();  // Consume newline

        if (noteIndex >= 0 && noteIndex < notes.size()) {
            System.out.println("Deleting note: " + notes.get(noteIndex));
            noteService.deleteNoteByIndex(noteIndex);
            System.out.println("Note deleted.");
        } else {
            System.out.println("Invalid note number.");
        }
    }
}

    public static void main(String[] args) {
        NoteAppMenu app = new NoteAppMenu("path_to_your_database");
        app.start();
    }
}