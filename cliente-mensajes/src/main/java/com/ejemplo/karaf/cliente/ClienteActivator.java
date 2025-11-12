package com.ejemplo.karaf.cliente;

import com.ejemplo.karaf.servicio.api.MensajeService;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;

public class ClienteActivator implements BundleActivator {

    @Override
    public void start(BundleContext context) {
        ServiceReference<?> ref = context.getServiceReference(MensajeService.class.getName());
        if (ref != null) {
            MensajeService servicio = (MensajeService) context.getService(ref);
            System.out.println("[Cliente-Mensajes] Servicio obtenido: " + servicio.obtenerMensaje());
        } else {
            System.out.println("[Cliente-Mensajes] Servicio MensajeService no disponible aún.");
        }
    }

    @Override
    public void stop(BundleContext context) {
        System.out.println("[Cliente-Mensajes] Bundle detenido.");
    }
}
