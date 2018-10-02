import {Injectable} from '@angular/core';

import{
    HttpEvent,
    HttpInterceptor,
    HttpHandler,
    HttpRequest
} from '@angular/common/http'
import { Observable } from '../../../node_modules/rxjs';


@Injectable()
export class Interceptor implements HttpInterceptor{
    intercept(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>>{
        console.log("This message is intercepted");
        console.log(req);
        return next.handle(req);
    }
}