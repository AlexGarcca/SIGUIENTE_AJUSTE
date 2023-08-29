package com.mycompany.act3;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ACT3 {
    public static void main(String[] args) {
        int memoriaTotal = 2000;
        int memoriaUtilizada = 200;  
        Scanner scanner = new Scanner(System.in);

        List<Particion> particiones = new ArrayList<>();
        List<Proceso> procesos = new ArrayList<>();

        System.out.print("Ingrese el número de particiones adicionales: ");
        int numParticiones = scanner.nextInt();

        for (int i = 0; i < numParticiones; i++) {
            System.out.print("Ingrese el tamaño de la partición " + (i + 1) + ": ");
            int tamanoParticion = scanner.nextInt();
            particiones.add(new Particion("Partición " + (i + 1), tamanoParticion));
        }
        
        
        procesos.add(new Proceso("java", 70));
        procesos.add(new Proceso("word", 200));
        procesos.add(new Proceso("paint", 40));

        int startIndex = 1; // Comenzar desde la partición 1

        for (Proceso proceso : procesos) {
            boolean asignado = false;

            if (proceso.nombre.equals("paint")) {
                for (Particion particion : particiones) {
                    if (!particion.ocupada && particion.tamano >= proceso.tamano) {
                        asignado = true;
                        particion.ocupada = true;
                        particion.nombreProceso = proceso.nombre;
                        System.out.println(proceso.nombre + " asignado a la partición " + particion.nombre);
                        memoriaUtilizada += proceso.tamano;
                        break;
                    }
                }
            } else {
                for (int j = startIndex; j < particiones.size(); j++) {
                    Particion particion = particiones.get(j);

                    if (!particion.ocupada && particion.tamano >= proceso.tamano) {
                        asignado = true;
                        startIndex = j + 1;  // Avanzar el índice de inicio
                        particion.ocupada = true;
                        particion.nombreProceso = proceso.nombre;
                        System.out.println(proceso.nombre + " asignado a la partición " + particion.nombre);
                        memoriaUtilizada += proceso.tamano;
                        break;
                    }
                }
            }

            if (!asignado) {
                System.out.println(proceso.nombre + " no pudo ser asignado a la memoria");
            }
            
            
            int memoriaRestante = memoriaTotal - memoriaUtilizada;
            if (memoriaRestante > 0) {
                particiones.add(new Particion("Partición " + (particiones.size() + 1), memoriaRestante));
            }
        }
            // Agregar nueva partición con memoria restante
            int memoriaRestante = memoriaTotal - memoriaUtilizada;
            if (memoriaRestante > 0) {
            particiones.add(new Particion("Partición " + (particiones.size() + 1), memoriaRestante));
            System.out.println("Se creó una nueva partición con " + memoriaRestante + " de memoria restante");
}

        int memoriaDisponible = memoriaTotal - memoriaUtilizada;
        if (memoriaDisponible < 0) {
            memoriaDisponible = 0; 
        }
        
        System.out.println("Memoria disponible: " + memoriaDisponible);

        
        scanner.close();
    }
}
