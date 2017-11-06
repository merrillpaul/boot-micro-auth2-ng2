# ProjectOneApp

This is Projectone angular app. This uses Angular 2 with type script.

##Basic requirements
Node JS > 6


## Technology Used
- Angular 2: Current Angular

## Style Guide
- The application follows angular style Guide https://angular.io/styleguide

## Development Setup
This site relies heavily on node and npm.

1. Make sure you are using at least node v.6+ and latest npm;
if not install [nvm](https://github.com/creationix/nvm) to get node going on your machine.

2. Clone project one repo https://github.com/pearca/ProjectOne.git

3. cd into root directory `application/projectone-ui/`

4. Install local npm packages by running `npm install`


## Development server

Run `ng serve` for a dev server. Navigate to `http://localhost:4200/`. The app will automatically reload if you change any of the source files.
## Code Architecture:
- This angular application follows modular development approach. Here is the sample code structure.
```
src/
 ├──app/                          
 |   ├──app-routing.module.ts     
 |   ├──app.component.htcssml      
 |   ├──app.component.html         
 |   ├──app.component.spec.ts      
 │   ├──app.component.ts           
 │   ├──app.component.ts           
 │   └──app.module.ts           
 │
 ├──core/                            /* The Core module should only imported
 |   ├──oauth.service.ts             /* in AppModule. It should contain single-use classes inside.
 │   │
 |   ├──...               
 │   │
 |   ├──...             
 │   │
 │   └── ...    
 │   
 │             
 │──shared/
 |   ├──http.service.ts               
 │   │
 |   ├──shared.module.ts            
 │   │
 │   └── ...                
 │   
 │               
 ├────feature/        
 |   ├──feature.component.ts            
 │   │
 |   ├──feature.component.html            
 │   │
 │   ├──feature.component.css  
 │   │        
 |   ├──feature-routing.module.ts            
 │   │
 |   ├──feature.filter.ts  
 │   │
 │   ├──feature.directive.ts  
 │   │
 │   ├──feature.component.spec.ts  
 │   │        
 |   ├──feature.module.ts
 │  .
 │  .
 ├────feature 2/  
```

 ## This project uses Angular 4

 Run below command

upgrade guide https://angular-update-guide.firebaseapp.com/

## Build

Run `ng build` to build the project. The build artifacts will be stored in the `dist/` directory. Use the `-prod` flag for a production build.

## Running unit tests

Run `ng test` to execute the unit tests via [Karma](https://karma-runner.github.io).

## Running end-to-end tests

Run `ng e2e` to execute the end-to-end tests via [Protractor](http://www.protractortest.org/).
Before running the tests make sure you are serving the app via `ng serve`.

## Further help

More text will be added for gradle soon.
