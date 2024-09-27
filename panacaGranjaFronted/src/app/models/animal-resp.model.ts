export interface PagedResponse<T> {
  content: T[];
  pageable: any;
  totalElements: number;
  totalPages: number;
  size: number;
  number: number;
  numberOfElements: number;
  first: boolean;
  last: boolean;
  empty: boolean;
}




export interface AnimalResp{
    
    id: number;
  nombre: string;
  especie: string;
  edad: number;
  peligroso: boolean;
  corralId: number;

}