package compiler2019;

import java.io.FileInputStream;

import analizadorLexico.LexerAnalyzer;
import analizadorLexico.Token; 
import globales.SymbolTable;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner; 

public class Compiler2019 {

    public static void main(String[] args) {
        printHeader(); 
        //loadFile(args[0]);
        loadFile("");

    }

    private static void loadFile(String path) { 
        if (path.equals("") || path.equals(null)) { // if there are not arguments, ask the user the file path
            Scanner scanner = new Scanner(System.in);
            System.out.println("Por favor, ingrese ruta de un archivo.");
            path = scanner.nextLine();
        }
        FileInputStream archivo;
        try {
            archivo = new FileInputStream(path);

            StringBuffer sb = new StringBuffer();
            while (archivo.available() != 0) {
                sb.append((char) archivo.read());
            }
            archivo.close();
            inflateMenu(sb);
        } catch (FileNotFoundException ex) {
            System.err.println("Archivo no encontrado. " + ex.getMessage());
        } catch (IOException ex) {
            System.err.println("IOException. " + ex.getMessage());
        }
    }

    private static void printHeader() {
        System.out.println("**************************************************************************");
        System.out.println("******      COMPILADOR PARA LA CÁTEDRA DISEÑO DE COMPILADORES       ******");
        System.out.println("******                ALUMNOS: Rojas - Lugo - Diez                  ******");
        System.out.println("******              AYUDANTE A CARGO: Dazeo, Nicolás                ******");
        System.out.println("******                          AÑO 2019                            ******");
        System.out.println("**************************************************************************");
        System.out.println("**************************************************************************\n");
    }

    private static void inflateMenu(StringBuffer sb) {
        boolean out = false;
        System.out.println("\n");
        Scanner scanner = new Scanner(System.in);
        while (!out) {
            printHeader();
            System.out.println("1. Analizador Léxico. (TP1)");
            System.out.println("2. Analizador Sintáctico. (TP2)");
            System.out.println("3. (TP3)");
            System.out.println("4. (TP4)");
            System.out.println("5. Cargar un nuevo archivo.");
            System.out.println("6. Salir");
            System.out.println("Elija una opción.");
            try {
                int option = scanner.nextInt();
                switch (option) {
                    case 1:
                        lexer(sb);
                        break;
                    case 2:
                        //parser(new LexerAnalyzer(sb));
                        break;
                    case 3:
                        break;
                    case 4:
                        break;
                    case 5:
                        loadFile("");
                        break;
                    case 6:
                        out = true;
                        break;
                    default:
                        System.out.println("Opción inválida.");
                        break;
                }
            } catch (InputMismatchException e) {
                System.out.println("Debe ingresar un número.");
                scanner.next();
            }
        }
    }

    private static void lexer(StringBuffer sb) {
        LexerAnalyzer lexer = new LexerAnalyzer(sb);
        Token t;
        System.out.println("*******************************************************");
        System.out.println("*********       ETAPA DE ANÁLISIS LÉXICO       ********");
        System.out.println("*******************************************************\n");
        System.out.println("______________________________________________________________");
        System.out.println("TOKENS RECONOCIDOS Y ERRORES:");
        System.out.println("______________________________________________________________\n");
        while ((t = lexer.getToken()) != null) {
            System.out.println(t.toString());
        }
        System.out.println("\n______________________________________________________________");
        System.out.println("Tabla de Simbolos: \n" + SymbolTable.getInstance().print());
        Scanner scan = new Scanner(System.in);
        System.out.println("Presione enter para continuar."); 
        scan.nextLine();
    }

}
