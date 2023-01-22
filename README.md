# GrayscaleToBinary

Pentru implementarea proiectului am avut doua pachete: packWork si packTest.
&nbsp	Primul pachet contine urmatoarele clase:
	Buffer
	Consumer
	GrayscaleToBinary
	Image
	ImageInterface
	Producer
	ReadImage
	WriterResults



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








