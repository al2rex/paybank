# PERFORMANCE

## Objetivo
Validar que el componente soporta **30 transacciones por segundo (TPS)** de manera sostenida, sin degradación significativa en latencia ni errores.

## Tipos de pruebas
- **Load Test**: Simular tráfico constante de 30 TPS para verificar estabilidad.
- **Stress Test**: Incrementar progresivamente la carga hasta encontrar el punto de quiebre.
- **Soak Test**: Mantener 30 TPS durante varias horas para detectar fugas de memoria o degradación.

## Herramientas
- [Locust](https://locust.io/) → pruebas distribuidas y escalables.
- [k6](https://k6.io/) → scripting en JavaScript, integración CI/CD.
- [Apache JMeter](https://jmeter.apache.org/) → alternativa clásica si no se dispone de las anteriores.

## Escenarios de prueba
1. **Carga normal (30 TPS)**
    - Duración: 10 minutos.
    - Métricas: latencia promedio < 200 ms, error rate < 1%.

2. **Carga creciente (Stress)**
    - Incremento: de 10 TPS hasta 100 TPS en pasos de 10.
    - Métricas: identificar el TPS máximo antes de que la latencia supere 1s o el error rate > 5%.

3. **Carga prolongada (Soak)**
    - Duración: 2 horas a 30 TPS.
    - Métricas: estabilidad de memoria, CPU y conexiones, sin degradación acumulativa.

## Métricas recolectadas
- Latencia promedio y percentiles (p95, p99).
- Throughput real alcanzado.
- Tasa de errores.
- Uso de CPU, memoria y conexiones.

## Criterios de aceptación
- El sistema debe soportar **30 TPS sostenidos** con latencia < 200 ms (p95).
- Error rate ≤ 1% en condiciones normales.
- Sin degradación significativa en pruebas prolongadas.
