/* Projeyi Hazırlayanlar:
 * SAFİYE SENA MERDİN
 * NİLAY KOÇ
 * EDA SINAR
 * MUSTAFA BAŞKONUŞ
 
 Bu program; hasta randevu alma sistemidir.
 programda, fonksiyon içinde başka fonksiyon çağırma özelliği kullanılmıştır. Dolayısıyla bazı fonksiyonlar parametre almamıştır.
 Programda, doktor ve hasta olmak üzere 2 adet kullanıcı girişi vardır.
 Programda; doktor, id numarasını girerek hangi tarihlerde hastası olduğunu görebilmektedir.
 Programda; hasta randevu alıp, id numarasını girerek hangi tarihlerde randevusu olduğunu görebilmektedir.
 Hasta kayıt olabilir ve aynı zamanda kaydını silebilir
 Hasta kayıt olurken sistem, kullanıcıya rastgele bir id numarası verir.
 Hasta kayıtlı id numarasını girdiğinde hastanın bilgileri ekranda yazar.
 Hasta randevu alabilir, aynı zamanda aldığı randevusunu iptal edebilir.
 UYARI:
 PROGRAM, TEK SEFERDE İKİ KAYIT SİLEMEMEKTEDİR!!! BAŞKA HASTA DAHA SİLMEK İÇİN PROGRAMI YENİDEN BAŞLATMAK GEREKMEKTEDİR.
 AKSİ TAKDİRDE, PROGRAM HATA VERİR.	
*/

#include <stdio.h>    
#include <string.h>
#include <stdlib.h>
#include <conio.h>
#include <windows.h>
#include <dos.h>
#include <dir.h>		
#include <unistd.h>   
#include <time.h>

//global değişkenler

FILE *fp, *fp2;  //fİLE türünde 2 adet pointer tanımlandı.
char *filename;  //fp tarafından tutulan pointer
char *filename2; //fp2 tarafından tutulan pointer
char secilenRandevu;
int secilenBirim;
int silinecekHasta;
int drID;
int hastaID;
int hastaNo;  //fonksiyon parametresi
int iptalOrAlma;
int satirNo=1;	//randevu sayisini bulur, 
int satirNoRnd=1; //kayıtlı hasta sayısını bulur.
int mevcutHasta; //hastanın dosyadaki sırası
char gelenVeri [100][100];  //dosyadan gelen verileri tutan dizi
char *okunanHastalar[100][100]; //dosya okunurken okunan hastaları tutan pointer dizi
//global fonksiyonlar
void girisEkraniLed();  //ortak 
void dosyaYazdir();		//ortak
void doktorRndGetir();	//MUSTAFA BAŞKONUŞ
void girisEkrani();		//NİLAY KOÇ
void randevuSec();		//SENA MERDİN
void birimSec();		//SENA MERDİN
void hastaBul();		//EDA SINAR
void hastaEkle();		//MUSTAFA BAŞKONUŞ
void hastaSil();		//EDA SINAR
void randevuSil();		//NİLAY KOÇ

//ekrandaki yazıların rengini ayarlayan fonksiyon (hazır)
void SetColor(int ForgC)  
 {
     WORD wColor;

      HANDLE hStdOut = GetStdHandle(STD_OUTPUT_HANDLE);
      CONSOLE_SCREEN_BUFFER_INFO csbi;

     if(GetConsoleScreenBufferInfo(hStdOut, &csbi)){
          wColor = (csbi.wAttributes & 0xF0) + (ForgC & 0x0F);
          SetConsoleTextAttribute(hStdOut, wColor);
     }
}

//sisteme hasta ekleyen fonksiyon.
void hastaEkle(){
       
	srand(time(NULL)); //her defasında farklı sayı üretir.
	
	fp = fopen("hastaVeriKayitli.txt", "a+");  //hasta dosyasını açar ve üzerine hastayı kaydeder.
	 
		char tc[12], isim[30], soyisim[30];  //Tc, isim ve soyad kaydetmek için diziler
        printf("TC: ");
        scanf("%11s", tc);
        printf("ISIM: ");
        scanf("%30s", isim);
        printf("SOYAD: ");
        scanf("%30s", soyisim);
        
	hastaID = 100+rand()%2000;  //hastaya her defasında farklı ıd verir.(time ile) (100-2000 arası) , global değişken

    fprintf(fp,"%d-%s-%s-%s\n",hastaID, tc, isim, soyisim ); //dizideki bilgileri satır satır dosyaya atar.
   
    fclose(fp); //açık olan dosyayı kapatır.
		
	printf("\nYeni ID numarasi: %d\n\n\n\t\t\tKayit isleminiz basarili\n\t\t\t\nSIMDI GIRIS YAPABILIRSINIZ...", hastaID);

} 

//ekrana dosyaları satır satır yazdıran fonksiyon
void dosyaYazdir(){   
	
	fp = fopen(filename, "r");
    if (fp == NULL){    //eğer dosya boş ise;
        printf("Dosya acilamadi %s",filename);
    }
    while (fgets(gelenVeri[satirNo], 100, fp) != NULL){  //dosyadaki verileri diziye aktarır.
        printf("%d- %s",satirNo, gelenVeri[satirNo]);   //ekrana diziyi yazdırır.
        satirNo++;     
	}
	printf("\n");
    fclose(fp);	//dosya kapatma
}

//doktor girişinde; ekrana, doktora ait randevuları getirir.
void doktorRndGetir(int id){	//parametresi int olan void fonksiyon
	
	if(id==1){	 //eğer doktorun id'si 1'e eşitse aşağıdaki işlemleri yapar.
			printf("Doktor Adi: FIKRET ERALP\n");
			filename = "genelCerrahiAlinanRandevu.txt"; //adlı dosya ekrana yazdırılır.
			dosyaYazdir();  
			printf("\n");
			satirNo=1;
			girisEkrani();	//giriş ekranına geri döner (ana menüye)
	}
	else if(id==2){	//eğer doktorun id'si 2'ye eşitse aşağıdaki işlemleri yapar.
			printf("Doktor Adi: NILAY KOC\n");
			filename = "dahiliyeAlinanRandevu.txt"; //adlı dosya ekrana yazdırılır.
			dosyaYazdir();
			printf("\n");
			satirNo=1;
			girisEkrani();
	}
	else if(id==3){		//eğer doktorun id'si 3'e eşitse aşağıdaki işlemleri yapar.
			printf("Doktor Adi: EDA SINAR\n");
			filename = "gozAlinanRandevu.txt"; //adlı dosya ekrana yazdırılır.
			dosyaYazdir();
			printf("\n");
			satirNo=1;
			girisEkrani();
	}
	else if(id==4){		//eğer doktorun id'si 4'e eşitse aşağıdaki işlemleri yapar.
			printf("Doktor Adi: MUSTAFA BASKONUS\n");
			filename = "kbbAlinanRandevu.txt"; //adlı dosya ekrana yazdırılır.
			dosyaYazdir();
			printf("\n");
			satirNo=1;
			girisEkrani();
	}
	else if(id==5){		//eğer doktorun id'si 5'e eşitse aşağıdaki işlemleri yapar.
			printf("Doktor Adi: ELA ALTINDAG\n");
			filename = "cocukHastaliklariAlinanRandevu.txt"; //adlı dosya ekrana yazdırılır.
			dosyaYazdir();
			printf("\n");
			satirNo=1;
			girisEkrani();	
	}
	else if(id==6){		//eğer doktorun id'si 6'ya eşitse aşağıdaki işlemleri yapar.
			printf("Doktor Adi: SENA MERDIN\n");
			filename = "psikiyatriAlinanRandevu.txt"; //adlı dosya ekrana yazdırılır.
			dosyaYazdir();
			printf("\n");
			satirNo=1;
			girisEkrani();	
	}
	else if(id==7){		//eğer doktorun id'si 7'ye eşitse aşağıdaki işlemleri yapar.
			printf("Doktor Adi: SUAT BIRTAN\n");
			filename = "kardiyolojiAlinanRandevu.txt"; //adlı dosya ekrana yazdırılır.
			dosyaYazdir();
			printf("\n");
			satirNo=1;
			girisEkrani();	
	}
	else if(id==8){		//eğer doktorun id'si 8'e eşitse aşağıdaki işlemleri yapar.
			printf("Doktor Adi: LEVENT ATAHANLI\n");
			filename = "onkolojiAlinanRandevu.txt"; //adlı dosya ekrana yazdırılır.
			dosyaYazdir();
			printf("\n");
			satirNo=1;
			girisEkrani();	
        }
	else{
		printf("Boyle bir doktor bulunmamaktadir!!! ");  //hasta 1-9 arası sayı girmezse ekrana bunu basar.
		sleep(2);
		system("cls");
		girisEkrani();	//giriş ekranına geri döner.
	}
}

//Ana menü sayfası fonksiyonu
void girisEkrani(){		
	
	SetColor(2);  	//  Ana menü sayfasındaki rengi ayarlar.
	int kullaniciTur;	//kullanıcının hasta yada doktor olduğunu belirten değişken.
	printf("\t\t\t\t***Ana Sayfa***\n\n1-DOKTOR GIRISI\n2-HASTA GIRISI\n3-YENI HASTA KAYIT\n4-HASTA KAYIT SIL\n5-CIKIS YAP");
	printf("\n\nlutfen seciniz:");	scanf("%d", &kullaniciTur);
	
	system("cls");			//yeni sekme açan fonksiyon
	
	if(kullaniciTur==1){
		printf("Doktor ID:");	scanf("%d", &drID);
		doktorRndGetir(drID);  //eğer kullanıcı türü doktorsa, ekrana doktora ait randevuları getiren fonksiyon çağrılır.
	}
	
	if(kullaniciTur==2){		//eğer kullanıcı türü hastaysa, ekrana hastaBul() fonksiyonunu çağırır.
		printf("Hasta ID:");	scanf("%d", &hastaID);
		hastaBul(hastaID); 	//hasta bilgilerini ekrana yazar.
		birimSec();			//birim seç fonksiyonu çağrılır hastadan birim seçmesi istenir.
	
	}
	
	if(kullaniciTur==3){  //hasta eklemek için 3'e basılır.
		hastaEkle();		//hasta ekleme fonk. çağrılır.
		sleep(3);
		system("cls");		//yeni sekme açar.
		girisEkrani();		//giriş ekranı fonk çağrılır.
	}
	
	if(kullaniciTur==4){		//eğer kullanıcı türü hastaysa, ekrana hasta sil fonk çağrılır.
		printf("Hasta ID:");	scanf("%d", &hastaID);
		hastaSil(hastaID);		//silinecek hastanın id si istenir. 
	
	}
	
	if(kullaniciTur==5){		////eğer kullanıcı; 5'e basarsa programdan çıkış yapar.
			printf("\t\t\t\t...GECMIS OLSUN...\n\t\t\t\tSAGLIKLI GUNLER DILERIZ");
			exit(0);		
		}
}

//ekrana randevu tarihlerini getirip, seçilen randevuyu silen ve alınan randevular dosyasına atayan fonksiyon.
void randevuSec(){	
	
	SetColor(1);  //ekrandaki yazının rengini ayarlayan fonksiyon.
	dosyaYazdir();
    int secilenRandevu;
    printf("\nLutfen size uyan bir randevu tarihi seciniz:\n\n");
    printf("0-CIKIS\n:");
    scanf("%d",&secilenRandevu);
    
    if(secilenRandevu==0){  //kullanıcı 0'a basarsa çıkış yapar.
		system("cls");
		girisEkrani();
	}
	if(secilenRandevu!=0){  
	
		fp = fopen(filename, "w");  //boş randevu dosyasını açar seçilen randevuyu yazmaz.
		fp2 = fopen(filename2, "a");  //seçilen randevuyu dolu dosyasına atar.
		
		for(int i=0;i<satirNo;i++){
			if(i!=secilenRandevu){
				fputs(gelenVeri[i], fp);  //seçilen randevu hariç diğer bilgileri yazar.
			}
			
			if(i==secilenRandevu){     	//seçilen randevuyu dolu dosyasına gönderir, üzerine yazar.
				printf("\n>>>>> %s", strcat(okunanHastalar[mevcutHasta][1], gelenVeri[i])); //mevcut hastanın tc'sini ve seçtiği randevuyu birleştirip yan yana yazar.
				fputs(gelenVeri[i], fp2); //seçilen randevuyu alınan randevular dosyasına koyar.
			}			
		}
		fclose(fp); //dosya kapatma
		fclose(fp2); //dosya kapatma
		satirNo=1;
		printf("\n\t\t\tRANDEVU ALINIMI BASARILI\n");
		sleep(5);
		system("cls");
		girisEkrani(); // Giriş ekranına geri döner.
	}
}
//seçilen randevuyu siler ve alınmayanlar dosyasına gönderir. Randevu alma fonk. tersi yönde çalışır.
void randevuSil(){	
	
	dosyaYazdir();
	int silinenRandevu;
	printf("\nLutfen iptal etmek istediginiz kendinize ait randevu tarihini seciniz:\n\n");
    printf("0-CIKIS\n:");
    scanf("%d",&silinenRandevu);
    
    if(silinenRandevu==0){  //kullanıcı 0'a basarsa çıkış yapar.
		system("cls");
		girisEkrani();
	}
	if(silinenRandevu!=0){  
	
		fp = fopen(filename, "w");  //boş randevu dosyasını açar seçilen randevuyu yazmaz.
		fp2 = fopen(filename2, "a");  //seçilen randevuyu dolu dosyasına atar.
		
		for(int i=0;i<satirNo;i++){
			if(i!=silinenRandevu){
				fputs(gelenVeri[i], fp);  //seçilen randevu hariç diğer bilgileri yazar.
			}
			
			if(i==silinenRandevu){     	//seçilen randevuyu dolu dosyasına gönderir, üzerine yazar.
				printf("\n>>>>> %s", strcat(okunanHastalar[mevcutHasta][1], gelenVeri[i])); //mevcut hastanın tc'sini ve seçtiği randevuyu birleştirip yan yana yazar.
				fputs(gelenVeri[i], fp2); //seçilen randevuyu alınmayan randevular dosyasına koyar.
			}			
		}
		fclose(fp); //dosya kapatma
		fclose(fp2); //dosya kapatma
		satirNo=1;
		printf("\n\t\t\tRANDEVU SILME BASARILI\n");
		sleep(4);
		system("cls");
		girisEkrani(); // Giriş ekranına geri döner.
	}
}

//Hastanın seçtiği ilk numaraya göre birimsec() fonk çağırılır. 
//ikinci seçtiği numaraya göre de  randevuSec() yada randevuSil() fonk çağırılır. 
void birimSec(){	
	
	printf("1-GENEL CERRAHI\n2-DAHILIYE\n3-GOZ\n4-KBB\n5-COCUK HASTALIKLARI\n6-PSIKIYATRI\n7-KARDIYOLOJI\n8-ONKOLOJI\n9-Ana Menu");
	printf("\n\nLutfen BIRIM seciniz:\n ");	scanf("%d", &secilenBirim);
	system("cls");
	
	printf("0-CIKIS\n1-RANDEVU AL\n2-RANDEVU SIL\n"); scanf("%d", &iptalOrAlma);
	
	if(iptalOrAlma==0){  //hasta, 0'a bastıysa giriş ekranına geri döner.
		system("cls");
		girisEkrani();
	}
	switch(secilenBirim){
		case 1: //hasta, 1'e bastıysa genel cerrahinin boş randevuları ekrana gelir.
			if(iptalOrAlma==1){
				filename = "genelCerrahiBos.txt"; filename2 = "genelCerrahiAlinanRandevu.txt";
				printf("\nDoktor Adi: FIKRET ERALP\n\n");
				randevuSec();	//randevu seçme fonk çağrılır.
			}
			if(iptalOrAlma==2){
				filename = "genelCerrahiAlinanRandevu.txt"; filename2 = "genelCerrahiBos.txt";
				printf("\nDoktor Adi: FIKRET ERALP\n\n");
				randevuSil();	//randevuyu iptal etme fonk çağrılır.	
		}
		break;
		
		case 2: //hasta, 2'ye bastıysa dahiliyenin boş randevuları ekrana gelir.
			if(iptalOrAlma==1){
				filename = "dahiliyeBos.txt"; filename2 = "dahiliyeAlinanRandevu.txt";  // 2 adet dosya açar.
				printf("\nDoktor Adi: NILAY KOC\n\n");
				randevuSec();	//randevu seçme fonk çağrılır.
			}
			if(iptalOrAlma==2){
				filename = "dahiliyeAlinanRandevu.txt"; filename2 = "dahiliyeAlinanBos.txt";  // 2 adet dosya açar.
				printf("\nDoktor Adi: NILAY KOC\n\n");
				randevuSil();  //randevuyu iptal etme fonk çağrılır.
			}	
		break;
		
		case 3: //hasta, 3'e bastıysa genel cerrahinin boş randevuları ekrana gelir.
			if(iptalOrAlma==1){
				filename = "gozBos.txt"; filename2 = "gozAlinanRandevu.txt";   // 2 adet dosya açar.
				printf("\nDoktor Adi: EDA SINAR\n\n");
				randevuSec();	//randevu seçme fonk çağrılır.			
			}
			if(iptalOrAlma==2){
				filename = "gozAlinanRandevu.txt"; filename2 = "gozBos.txt";   // 2 adet dosya açar.
				printf("\nDoktor Adi: EDA SINAR\n\n");
				randevuSil();	//randevuyu iptal etme fonk çağrılır.
			}
		break;
		
		case 4: //hasta, 4'e bastıysa kbb'nin boş randevuları ekrana gelir.
			if(iptalOrAlma==1){
				filename = "kbbBos.txt"; filename2 = "kbbAlinanRandevu.txt";   // 2 adet dosya açar.
				printf("\nDoktor Adi: MUSTAFA BASKONUS\n\n");
				randevuSec();	//randevu seçme fonk çağrılır.			
			}
			if(iptalOrAlma==2){
				filename = "kbbAlinanRandevu.txt"; filename2 = "kbbAlinanBos.txt";   // 2 adet dosya açar.
				printf("\nDoktor Adi: MUSTAFA BASKONUS\n\n");
				randevuSil();	//randevuyu iptal etme fonk çağrılır.			
			}
		break;
		
		case 5: //hasta, 5'e bastıysa çocuk hastalıklarının boş randevuları ekrana gelir.
			if(iptalOrAlma==1){
				filename = "cocukHastaliklariBos.txt"; filename2 = "cocukHastaliklariAlinanRandevu.txt";   // 2 adet dosya açar.
				printf("\nDoktor Adi: ELA ALTINDAG\n\n");
				randevuSec();	//randevu seçme fonk çağrılır.			
			}
			if(iptalOrAlma==2){
				filename = "cocukHastaliklariAlinanRandevu.txt"; filename2 = "cocukHastaliklariBos.txt";   // 2 adet dosya açar.
				printf("\nDoktor Adi: ELA ALTINDAG\n\n");
				randevuSil();	//randevuyu iptal etme fonk çağrılır.
			}
		break;
		
		case 6: //hasta, 6'ya bastıysa psikiyatri boş randevuları ekrana gelir.
			if(iptalOrAlma==1){
				filename = "psikiyatriBos.txt"; filename2 = "psikiyatriAlinanRandevu.txt";   // 2 adet dosya açar.
				printf("\nDoktor Adi: SENA MERDIN\n\n");
				randevuSec();	//randevu seçme fonk çağrılır.			
			}
			if(iptalOrAlma==2){
				filename = "psikiyatriAlinanRandevu.txt"; filename2 = "psikiyatriBos.txt";   // 2 adet dosya açar.
				printf("\nDoktor Adi: SENA MERDIN\n\n");
				randevuSil();	//randevuyu iptal etme fonk çağrılır.
			}
		break;
		
		case 7: //hasta, 7'bastıysa kardiyolojinin boş randevuları ekrana gelir.
			if(iptalOrAlma==1){
				filename = "kardiyolojiBos.txt"; filename2 = "kardiyolojiAlinanRandevu.txt";   // 2 adet dosya açar.
				printf("\nDoktor Adi: SUAT BIRTAN\n\n");
				randevuSec();	//randevu seçme fonk çağrılır.			
			}
			if(iptalOrAlma==2){
				filename = "kardiyolojiBos.txt"; filename2 = "kardiyolojiAlinanRandevu.txt";   // 2 adet dosya açar.
				printf("\nDoktor Adi: SUAT BIRTAN\n\n");
				randevuSil();	//randevuyu iptal etme fonk çağrılır.
			}
		break;
		
		case 8: //hasta, 8'e bastıysa onkolojinin boş randevuları ekrana gelir.
			if(iptalOrAlma==1){
				filename = "onkolojiBos.txt"; filename2 = "onkolojiAlinanRandevu.txt";   // 2 adet dosya açar.
				printf("\nDoktor Adi: LEVENT ATAHANLI \n\n");
				randevuSec();	//randevu seçme fonk çağrılır.			
			}
			if(iptalOrAlma==2){
				filename = "onkolojiAlinanRandevu.txt"; filename2 = "onkolojiBos.txt";   // 2 adet dosya açar.
				printf("\nDoktor Adi: LEVENT ATAHANLI \n\n");
				randevuSil();	//randevuyu iptal etme fonk çağrılır.
			}
		break;
		
		case 9: //hasta, 9'a bastıysa giriş ekranına geri döner.
			girisEkrani();	
			system("cls");
			break;
    }
}

//Hasta; id numrasını girdikten sonra o hastaya ait Id, Tc, İsim ve Soyİsim bilgilerini ekrana getirir.
void hastaBul(int hastaNo){
	
	FILE *hastaVeri = fopen ( "hastaVeriKayitli.txt", "r" );  //dosyasını açar.
    //döngü için gerekli değişkenler
    int i=0;
    int j=0;
    int k=0;
    
    char hastaBilgileri[100];	//dosyadan gelen bilgileri tutan dizi
    char *deger;	//strtok ile ayrılan değerlerin adresini tutan pointer

    for(i=0; i<=9; i++){	//okunan değerleri NULL'a çeviren döngü
        for(j=0;j<=4;j++){
            okunanHastalar[i][j] = NULL;
        }
    }
    i=0;

    while(fgets(hastaBilgileri, sizeof(hastaBilgileri), hastaVeri)){  //dosyadaki dizileri tek tek ayıran döngü ve diziye atar.
        
        deger = strtok(hastaBilgileri, ",-");
        okunanHastalar[i][0] = strdup(deger);
        
        deger = strtok(NULL, ",-");
        okunanHastalar[i][1] = strdup(deger);

        deger = strtok(NULL, ",-");
        okunanHastalar[i][2] = strdup(deger);

        deger = strtok(NULL, "-\n");
        okunanHastalar[i][3] = strdup(deger);
        
        
        i++;
        satirNoRnd++;
    }   

	int bulunan=0;
    for(k=0; k<satirNoRnd-1; k++){
		int x; //kullanıcının girdiği id 
		sscanf(okunanHastalar[k][0], "%d", &x);  //girilen sayı ile satırın(dizinin) ilk elemanını yani id'yi karşılaştırır.
		if(x==hastaNo){  // kullanıcın girdiği sayı ile ayni id'yi bulur.
			system("cls");
			printf("Hasta ID: %d\n", hastaID); //ekrana id yi yazar.
			printf("Hasta TC: %s\n", okunanHastalar[k][1]); //ekrana bulunan hastanın tc sini yazar.
			printf("Hasta Adi: %s\n", okunanHastalar[k][2]); //ekrana bulunan hastanın ismini yazar.
			printf("Hasta Soyadi: %s\n\n", okunanHastalar[k][3]); //ekrana bulunan hastanın soyismini yazar.
			mevcutHasta=k;
			satirNo=1;
			birimSec(); //hastayı bulduktan sonra birim seç fonk çağrılır.
			bulunan++;	//döngü sonunda bulunan hasta sayısını arttır.
			
		}
	}
	
	if(bulunan==0){  //eğer hiç hasta bulunmadıysa ekrana bulunamadı yazar ve giriş ekranına geri döner.
			printf("Boyle bir hasta bulunmamaktadir!!!");
			sleep(2);
			system("cls");
			girisEkrani();
	}

	fclose(hastaVeri);  //hasta dosyasını kapatır.
}

void hastaSil(int hastaNo){
	
	int l=100;
	int m=100;
	char **dizi = (char**) calloc(l, sizeof(char*));

	for (int i= 0 ; i < l; i++){
		dizi[i] = (char*) calloc(m, sizeof(char));
	}
	
	fp = fopen("hastaVeriKayitli.txt", "r");  //hasta dosyasını açar ve okur.
    
    if (fp == NULL){	//eğer dosya boş ise
        printf("Dosya acilamadi %s",filename);
    }
    while (fgets(dizi[satirNo], l, fp) != NULL){
     
        satirNo++;     
	}
		printf("\n");
		fclose(fp);
	
	int x; 
	FILE *hastaVeri2 = fopen( "hastaVeriKayitli.txt", "r" );
    int i=0;
    int j=0;
    char hastaBilgileri2[100];  //dosyadan gelen bilgileri tutan dizi
    char *deger2; //strtok ile ayrılan bilgileri tutan pointer

    for(i=0; i<=9; i++){	//okunan hastaları NULL'a çevirir.
        for(j=0;j<=4;j++){
            okunanHastalar[i][j] = NULL;
        }
    }
    i=0;

    while(fgets(hastaBilgileri2, sizeof(hastaBilgileri2), hastaVeri2)){
        
        deger2 = strtok(hastaBilgileri2, ",-");
        okunanHastalar[i][0] = strdup(deger2);
        sscanf(okunanHastalar[i][0], "%d", &x); //satırın ilk elemanı ile(id) kullanıcıdan alınan sayı karşılaştırılır. 
		
		if(x==hastaNo){
			silinecekHasta=i+1;
		}
		
        deger2 = strtok(NULL, ",-");
        okunanHastalar[i][1] = strdup(deger2);

        deger2 = strtok(NULL, ",-");
        okunanHastalar[i][2] = strdup(deger2);

        deger2 = strtok(NULL, "-\n");
        okunanHastalar[i][3] = strdup(deger2);
        
        i++;
        satirNoRnd++;
    }
    
    fclose(hastaVeri2); //hastayı bulduktan sonra dosyayı kapatır, ve tekrar aynı dosyayı açar.
    
    fp = fopen("hastaVeriKayitli.txt", "w"); //ve tekrar aynı dosyayı açar.
    
    for(int k=0;k<satirNoRnd;k++){
		if(k+1!=silinecekHasta){	//silinmesi istenen kişi hariç diğer hastaları dosyaya yazar.
			fputs(dizi[k+1], fp);
		}
    }
    
    printf("\t\t\t\tKaydiniz Basariyla Silinmistir\n");
	fclose(fp);  //dosya kapatma
    satirNoRnd=1;
    
	for (int i = 0; i < l; i++ ){
		free(dizi[i]); //silinen hastayı hafızadan siler ve  serbest bırakır.
	}

	free(dizi);  //hafızada daha önceden yer ayrılan dizi serbest bırakılır.
    
    sleep(1);
	system("cls");
    girisEkrani(); //giriş ekranına geri döner.
}

//Sistem açılış sayfasıdır.
void girisEkraniLed(){	
	
	printf("\t\t\t\tM");
	system("color 02");
	sleep(1);
	printf("E");
	system("color 03");
	sleep(1);
	printf("N");
	system("color 04");
	sleep(1);
	printf("S");
	system("color 05");
	sleep(1);
	printf("  HOSPITAL\n\n\n");
	system("color 06");
	sleep(1);
	printf("\t\tHASTA\n");
	system("color 03");
	sleep(1);
	printf("\t\t\tRANDEVU\n");
	system("color 04");
	sleep(1);	
	printf("\t\t\t\tALMA\n");
	system("color 05");
	sleep(1);
	printf("\t\t\t\t\tSISTEMI\n");
	system("color 06");
	sleep(1);
	system("cls");
	printf("\t\t\t\t...HOSGELDINIZ...\n ");
	sleep(1);
	system("cls");
	printf("\t\t\t\tMENS HOSPITAL.");
	system("color 07");
	sleep(1);
	system("cls");
	printf("\t\t\t\tMENS HOSPITAL..");
	system("color 05");
	sleep(1);
	system("cls");
	printf("\t\t\t\tMENS HOSPITAL...");
	system("color 04");
	sleep(1);
	system("cls");
}

int main() {
	
	girisEkraniLed();
    girisEkrani();
    
    return 0;
}
