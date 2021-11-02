import { Byte } from "@angular/compiler/src/util";

export class Book {
    constructor(public isbn: string, public book_cover: Byte[], public title: string,
        public author: string, public publisher: string, public published: string, 
        public genre: string, public price: number, public summary: string) {}
}