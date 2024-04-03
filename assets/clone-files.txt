#!/bin/bash

#Guarda la hora de inicio
start_time=$(date +%s.%3N)

if [ "$#" -ne 2 ]; then
    echo "Uso: $0 [directorio] [numero total de copias]"
    exit 1
fi

directorio=$1
total_copias=$2

if [ ! -d "$directorio" ]; then
    echo "El directorio '$directorio' no existe."
    exit 1
fi

cd "$directorio"

archivos=(*)
num_archivos=${#archivos[@]}

if [ $num_archivos -eq 0 ]; then
    echo "No hay archivos en el directorio para copiar."
    exit 1
fi

n_copias=0

while [ $n_copias -lt $total_copias ]; do
    for archivo in "${archivos[@]}"; do
        if [ $n_copias -ge $total_copias ]; then
            break
        fi

        if [ -f "$archivo" ]; then
            ((n_copias++))
            nombre_copia="$(basename "$archivo")_copia$n_copias"
            cp "$archivo" "$nombre_copia"
            echo "Copia $n_copias de '$archivo' creada como '$nombre_copia'"
        fi
    done
done

#Calcula tiempo transcurrido
end_time=$(date +%s.%3N)
runtime=$(printf "%.3f" "$(echo"end_time - start_time"   bc -1)")

echo "Proceso completado. $n_copias archivos fueron creados."
echo "Tiempo de ejecucion: $runtime segundos"
