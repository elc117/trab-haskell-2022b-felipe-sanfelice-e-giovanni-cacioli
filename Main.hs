import System.IO
import System.Random
import Text.Printf
import Control.Monad

-- Função para gerar um número pseudoaleatório entre min e max
geraRandom :: Int -> Int -> IO Int
geraRandom min max = getStdRandom (randomR (min, max))

-- Função para gerar as coordenadas x e y
geraCoordenadas :: IO (Int, Int)
geraCoordenadas = do
  x <- geraRandom 0 900
  y <- geraRandom 0 900
  return (x + 50, y + 50)

-- Função para gerar uma lista
geraLista :: Int -> IO [(Int, Int)]
geraLista 0 = return []
geraLista n = do
  coordenadas <- geraCoordenadas
  resto <- geraLista (n - 1)
  return (coordenadas : resto)

-- Função para gerar o código SVG
geraSVG :: [(Int, Int)] -> String
geraSVG forma = do
  printf "<svg width='1000' height='1000' style='background-color: black;'>\n"
    ++ concatMap (\(x, y) -> 
        let dx = [90, 150, -150, 90, 150, -150, -90, -90]
            dy = [-30, -40, -40, 10, 10, 10, -60, 10]
            colors = ["blue", "purple", "yellow", "red", "orange", "pink", "green", "white"]
        in concatMap (\(dx, dy, color) -> 
            printf "<line x1='%d' y1='%d' x2='%d' y2='%d' style='stroke: %s; stroke-dasharray: 5;'/>\n" x (y - 30) (x + dx) (y + dy) color) (zip3 dx dy colors)
         ) forma
  ++ concatMap (\(x, y) -> printf "<polygon points='%d,%d %d,%d %d,%d' style='fill:yellow;'/>\n" (x - 12) (y - 20) (x + 12) (y - 20) x (y - 50)) forma
  ++ concatMap (\(x, y) -> printf "<polygon points='%d,%d %d,%d %d,%d' style='fill:yellow;'/>\n" (x - 12) (y - 40) (x + 12) (y - 40) x (y - 10)) forma
  ++ "</svg>"

main :: IO ()
main = do
  forma <- geraLista 10
  writeFile "output.svg" (geraSVG forma)
