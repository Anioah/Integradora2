
import time
import Adafruit_DHT

sensor = Adafruit_DHT.DHT11
pin = 4

while True:
    
    humedad, temperatura = Adafruit_DHT.read_retry(sensor, pin)
    
    if humedad is not None and temperatura is not None:
        print(f'Temperatura={temperatura:.2f}*C Humedad={humedad:.2f}%')
    
    else:
        print('Fallo la lectura del sensor. Intente de nuevo')
        
    time.sleep(5)