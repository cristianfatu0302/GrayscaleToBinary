# GrayscaleToBinary

Pentru implementarea proiectului am avut doua pachete: packWork si packTest.
	Primul pachet contine urmatoarele clase:
	Buffer
	Consumer
	GrayscaleToBinary
	Image
	ImageInterface
	Producer
	ReadImage
	WriterResults

Cel de al doilea pachet continue clasa Main in care se face testarea aplicatiei.
Clasa Image este o clasa abstracta  avand metoda abstracta readImageQuarters, metoda ce este implementata in clasa ReadImage fiind utilizata pentru citirea de sferturi a imaginii. Functia primeste ca parametru sfertul current si numele imaginii.  
Clasa ReadImage mosteneste clasa Image si implementeaza clasa ImageInterface. Pe langa functie pentru citirea sferturilor exista si o functie ReadFile din interfata ImageInterface functie ce este facuta pentru citirea numelului fisierului de intrare. 
Clasele Buffer, Consumer, Producer, WriterResults sunt utilizate pentru multi-threading.
In classa Buffer: avem o functie put si una get ce primeste ca parametru sfertul de imagine, functie ce este utilizata in Producer pentru a citit sferturile de imagine. In  functie get returnam sfertul de imagine corespunzator index-ului dat in consumer. Legatura dintre clasele consumer si producer se face prin clasa Buffer. 
In clasa Producer citim cate un sfert de imagine, il transmitem catre clasa Consumer unde se proceaseaza cate un sfert de imagine apoi este transmis catre clasa WriterResults care se ocupa de scrierea imaginii in noul fisier. Legatura dintre consumer si WriterResults se face cu ajutorul pype-urilor. Pentru a putea transmite sfertul de imagine catre WriterResults am transformat sfertul intr-un array de bytes pe care l-am transmis si apoi l-am transformat in imagine din nou. Dupa ce primesc sferturile imi reconstruiesc noua imagine urmand sa fie scrisa in noul fiesier de iesire. 
In clasa GrayscaleToBinary se proceseaza imaginea. Avem 2 functii:
ReconstructImage si processImage. Functia reconstructImage primeste sferturile imaginii si imi reconstruieste intreaga imagine. In functia processImage se prelucreaza imaginea. Mai intai calculez valoarea medie a fiecarui pixel in functie de rgb dupa formula r+g+b/3 si in functie de pragul static ales (127 fiind jumatatea lui 255)  daca media este mai mica setam culoarea negru, iar daca media este mai mare setam culoarea alb.
Mai jos este codul utilizat pentru procesarea imaginii. Procesarea se face pe sferturi in clasa Consumer primind ca parametru sfertul de imagine (de tipul BufferedImage). 

int width = grayscaleImage.getWidth();
int height = grayscaleImage.getHeight();

for(int i = 0; i < width; i++){
			for(int j = 0; j < height; j++){
				Color c = new Color(grayscaleImage.getRGB(i,  j));//descompunerea culorilor imaginii in valorile RGB
				
				int red = (int)(c.getRed());
				int green = (int)(c.getGreen());
				int blue = (int)(c.getBlue());
				
				int grayScale = (red+green+blue)/3; // se obtine valoarea medie daca setam pixelii la aceasta culoare vom avea nivelul de gri pentru fiecare pixel
						
				//conversia la binar
				//setam pixelii in alb sau negru
				if( grayScale < threshold){ 
					//setare culoarea negru
					red = 0;
					green = 0;
					blue = 0;
				} else {
					//setare culoarea alb
					red = 255;
					blue = 255;
					green = 255;
				}
				
				
				
				Color newColor = new Color(red, green, blue); //noua culoare
				grayscaleImage.setRGB(i, j, newColor.getRGB()); //noul pixel ia valoarea calculata
			}
	}

Imaginea inaintea procesarii:
 ![alt text](https://github.com/cristianfatu0302/GrayscaleToBinary/blob/master/golf.bmp)
Imaginea procesata, in binar:
 ![alt text](https://github.com/cristianfatu0302/GrayscaleToBinary/blob/master/golfBinary.bmp)








