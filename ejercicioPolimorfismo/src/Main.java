import notificaciones.Notificacion;
import notificaciones.medios.CorreoElectronico;
import notificaciones.medios.MensajeTexto;
import notificaciones.medios.NotificacionApp;
import notificaciones.situaciones.CancelacionClase;
import notificaciones.situaciones.ConfirmacionInscripcion;
import notificaciones.situaciones.PublicacionCalificaciones;
import notificaciones.situaciones.RecordatorioPago;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Collections;

public class Main {

    public static void main(String[] args) {

        System.out.println("╔══════════════════════════════════════════╗");
        System.out.println("║  SISTEMA DE NOTIFICACIONES UNIVERSITARIAS ║");
        System.out.println("╚══════════════════════════════════════════╝");

        // Caso 1: Calificaciones disponibles → correo electrónico
        Notificacion n1 = new Notificacion(
            "NTF-001",
            "Laura Gómez",
            new PublicacionCalificaciones("Estructuras de Datos", "2025-1"),
            new CorreoElectronico("laura.gomez@universidad.edu", "Calificaciones disponibles",
                Collections.emptyList())
        );

        // Caso 2: Recordatorio de pago → SMS
        Notificacion n2 = new Notificacion(
            "NTF-002",
            "Carlos Martínez",
            new RecordatorioPago(1850000.00, LocalDate.of(2025, 5, 15)),
            new MensajeTexto("+573001234567", "Claro")
        );

        // Caso 3: Cancelación de clase → notificación app Android
        Notificacion n3 = new Notificacion(
            "NTF-003",
            "Valentina Torres",
            new CancelacionClase("Dr. Ramírez", "Aula 204-B"),
            new NotificacionApp("token-abc-987xyz", "Android")
        );

        // Caso 4: Confirmación de inscripción → correo con adjuntos
        Notificacion n4 = new Notificacion(
            "NTF-004",
            "Miguel Ángel Ruiz",
            new ConfirmacionInscripcion("Congreso Nacional de Ingeniería", LocalDate.of(2025, 6, 10)),
            new CorreoElectronico("miguel.ruiz@universidad.edu", "Inscripción confirmada",
                Arrays.asList("programa_congreso.pdf", "credencial_digital.pdf"))
        );

        // Caso 5 (error): SMS con número inválido — demuestra estado FALLIDO
        Notificacion n5 = new Notificacion(
            "NTF-005",
            "Pedro Salcedo",
            new RecordatorioPago(500000.00, LocalDate.of(2025, 5, 1)),
            new MensajeTexto("numero-invalido", "Movistar")
        );

        // --- Polimorfismo en acción: mismo método enviar() sobre distintas combinaciones ---
        n1.enviar();
        n2.enviar();
        n3.enviar();
        n4.enviar();
        n5.enviar();

        // --- toString() de cada notificación ---
        System.out.println("\n╔══════════════════════════════════════════╗");
        System.out.println("║         DETALLE DE OBJETOS (toString)     ║");
        System.out.println("╚══════════════════════════════════════════╝");
        System.out.println(n1);
        System.out.println(n2);
        System.out.println(n3);
        System.out.println(n4);
        System.out.println(n5);

        // --- Resumen de estados ---
        System.out.println("\n╔══════════════════════════╗");
        System.out.println("║       RESUMEN ENVÍOS      ║");
        System.out.println("╠══════════════════════════╣");
        for (Notificacion n : new Notificacion[]{n1, n2, n3, n4, n5}) {
            System.out.printf("║ %-10s → %-10s ║%n", n.getCodigo(), n.getEstado());
        }
        System.out.println("╚══════════════════════════╝");
    }
}
