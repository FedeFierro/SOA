#define R 2
#define G 4
#define B 3

void setup(){
  Serial.begin(9600);
  Serial.println("iniciando..");
    pinMode(R, OUTPUT); //Configuração dos pinos como saída
    pinMode(G, OUTPUT);
    pinMode(B, OUTPUT);
}

void loop(){
    analogWrite(R, random(255)); //Ligando as portas PWM com valor aleatório de duty cicle
    analogWrite(G, random(255));
  analogWrite(B, random(255));
  Serial.println("Has Change");
    delay(200); //Espera 200ms até atualizar o LED com novos valores aleatórios
}
