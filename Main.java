import java.io.File;
import java.io.*;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

public class Main {
    public static void main(String[] args) {
        try {
            // Cria o arquivo svg
            File file = new File("output.svg");
            FileWriter writer = new FileWriter(file);

            // Insere o cabeçalho do svg
            writer.write("<svg width='1000' height='1000' xmlns='http://www.w3.org/2000/svg'>\n");
            writer.write("<rect width='100%' height='100%' fill='black'/>\n");

            // Cria gerador de números aleatórios
            Random rand = new Random();

            // Gera 20 estrelas
            for (int i = 0; i < 20; i++) {
                // Gera posição aleatória da estrela
                int x = rand.nextInt(900) + 50;
                int y = rand.nextInt(900) + 50;
                

                // Gera cor aleatória para a estrela
                String cor = String.format("#%06X", rand.nextInt(0xFFFFFF + 1));

                // Cria o triângulos da estrela e as linhas
               for (int j = 0; j < 15; j++) {
                
                  String corLinha = String.format("#%06X", rand.nextInt(0xFFFFFF + 1));
                
                 writer.write("<line x1='" + x + "' y1='" + (y - 30) + "' x2='" + (x + (70 * Math.cos(j * Math.PI / 3))) + "' y2='" + (y + (70 * Math.sin(j * Math.PI / 10))) + "' style='stroke: " + corLinha + "; stroke-width: 2; stroke-dasharray: 5 ;'/>\n");
                 
                 writer.write("<line x1='" + x + "' y1='" + (y - 30) + "' x2='" + (x + (70 * Math.cos(j * Math.PI / 3))) + "' y2='" + (y - (100 * Math.sin(j * Math.PI / 10))) + "' style='stroke: " + corLinha + "; stroke-width: 2; stroke-dasharray: 5 ;'/>\n");
                
               }

              writer.write("<polygon points='" + (x - 12) + "," + (y - 20) + " " + (x + 12) + "," + (y - 20) + " " + x + "," + (y - 45) + "' fill='yellow' />\n");
              
               writer.write("<polygon points='" + (x - 12) + "," + (y - 35) + " " + (x + 12) + "," + (y - 35) + " " + x + "," + (y - 10) + "' fill='yellow' />\n");
              
            }

            // Fecha o svg
            writer.write("</svg>");
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

