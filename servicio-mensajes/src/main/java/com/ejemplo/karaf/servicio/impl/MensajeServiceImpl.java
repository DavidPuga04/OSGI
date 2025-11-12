package com.ejemplo.karaf.servicio.impl;

import com.ejemplo.karaf.servicio.api.MensajeService;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceRegistration;

public class MensajeServiceImpl implements MensajeService, BundleActivator {

    private ServiceRegistration<?> registro;

    @Override
    public String obtenerMensaje() {
        return "¡Hola desde el servicio OSGi!";
    }

    @Override
    public void start(BundleContext context) {
        registro = context.registerService(MensajeService.class.getName(), new MensajeServiceImpl(), null);
        System.out.println("[Servicio-Mensajes] Servicio registrado correctamente.");
    }

    @Override
    public void stop(BundleContext context) {
        registro.unregister();
        System.out.println("[Servicio-Mensajes] Servicio detenido.");
    }
}
