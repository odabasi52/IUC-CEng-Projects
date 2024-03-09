#include <iostream>
#include <fstream>


void read_file(std::string f, std::string line){
    std::ifstream file(f);
    if (file.is_open()){
        while (std::getline(file, line)){
            std::cout << line << std::endl;
        }
    }
}

void write_file(std::string f, std::string line){
    std::ofstream file(f);
    if (file.is_open()){
        file << line << std::endl;
    }
}

int main(){
    std::fstream file("file.txt", std::ios::in | std::ios::out);
    if (file.is_open()){
        
        file.seekp(5);

        file << "Hello World!" << std::endl;
        file.seekg(10);
        std::string line;
        while (std::getline(file, line)){
            std::cout << line << std::endl;
        }
    }
}