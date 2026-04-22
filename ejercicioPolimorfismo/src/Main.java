import notificaciones.Destinatario;
import notificaciones.Notificacion;
import notificaciones.medios.CorreoElectronico;
import notificaciones.medios.MensajeTexto;
import notificaciones.medios.NotificacionApp;
import notificaciones.situaciones.CancelacionClase;
import notificaciones.situaciones.ConfirmacionInscripcion;
import notificaciones.situaciones.PublicacionCalificaciones;
import notificaciones.situaciones.RecordatorioPago;

import java.time.LocalDate;
import java.util.List;

public class Main {

    public static void main(String[] args) {

        System.out.println("╔══════════════════════════════════════════╗");
        System.out.println("║  SISTEMA DE NOTIFICACIONES UNIVERSITARIAS ║");
        System.out.println("╚══════════════════════════════════════════╝");

        // --- Destinatarios ---
        Destinatario laura   = new Destinatario("Laura Gómez",       "laura.gomez@universidad.edu",   "+573001111111", "token-laura-001");
        Destinatario carlos  = new Destinatario("Carlos Martínez",   "carlos.martinez@universidad.edu", "+573002222222", "token-carlos-002");
        Destinatario valeria = new Destinatario("Valeria Torres",    "valeria.torres@universidad.edu", "+573003333333", "token-valeria-003");
        Destinatario miguel  = new Destinatario("Miguel Ángel Ruiz", "miguel.ruiz@universidad.edu",   "+573004444444", "token-miguel-004");
        Destinatario sinDatos = new Destinatario("Sin Datos", "", "", "");

        // --- Caso 1: múltiples destinatarios, múltiples medios  ---
        // Situación: PublicacionCalificaciones → medios: correo + SMS + app
        Notificacion n1 = new Notificacion(
            "NTF-001",
            List.of(laura, carlos, valeria),
            new PublicacionCalificaciones("Estructuras de Datos", "2025-1"),
            List.of(
                new CorreoElectronico("Calificaciones disponibles"),
                new MensajeTexto(),
                new NotificacionApp()
            )
        );

        // --- Caso 2: un destinatario, un medio ---
        // Situación: RecordatorioPago → medio: SMS
        Notificacion n2 = new Notificacion(
            "NTF-002",
            List.of(carlos),
            new RecordatorioPago(1850000.00, LocalDate.of(2025, 5, 15)),
            List.of(new MensajeTexto())
        );

        // --- Caso 3: un destinatario, un medio ---
        // Situación: CancelacionClase → medio: app móvil
        Notificacion n3 = new Notificacion(
            "NTF-003",
            List.of(valeria),
            new CancelacionClase("Dr. Ramírez", "Aula 204-B"),
            List.of(new NotificacionApp())
        );

        // --- Caso 4: dos destinatarios, correo ---
        // Situación: ConfirmacionInscripcion → medio: correo
        Notificacion n4 = new Notificacion(
            "NTF-004",
            List.of(miguel, laura),
            new ConfirmacionInscripcion("Congreso Nacional de Ingeniería", LocalDate.of(2025, 6, 10)),
            List.of(new CorreoElectronico("Inscripción confirmada"))
        );

        // --- Caso 5 (error): destinatario sin datos de contacto → estado FALLIDO ---
        Notificacion n5 = new Notificacion(
            "NTF-005",
            List.of(sinDatos),
            new RecordatorioPago(500000.00, LocalDate.of(2025, 5, 1)),
            List.of(new CorreoElectronico("Pago pendiente"), new MensajeTexto())
        );

        // Polimorfismo
        n1.enviar();
        n2.enviar();
        n3.enviar();
        n4.enviar();
        n5.enviar();

        // --- toString() de cada notificación ---
        System.out.println("\n método toString");
        System.out.println(n1);
        System.out.println(n2);
        System.out.println(n3);
        System.out.println(n4);
        System.out.println(n5);

        // --- Resumen de estados ---
        System.out.println("\n; RESUMEN ENVÍOS");
        for (Notificacion n : new Notificacion[]{n1, n2, n3, n4, n5}) {
            System.out.printf("║ %-10s → %-10s ║%n", n.getCodigo(), n.getEstado());
        }

    }
}
