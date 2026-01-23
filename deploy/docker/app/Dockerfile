# Usamos una imagen base de Windows Server Core
FROM mcr.microsoft.com/windows/servercore:ltsc2022

# Directorio de trabajo dentro del contenedor
WORKDIR /app

# Copiamos el ejecutable nativo al contenedor
COPY ./bankpay-1.0.0-runner.exe .

# Comando por defecto al iniciar el contenedor
CMD ["C:\\app\\mi-app.exe"]
