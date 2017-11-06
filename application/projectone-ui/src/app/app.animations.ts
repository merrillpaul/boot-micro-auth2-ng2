import { animate, AnimationTriggerMetadata, state, style, transition, trigger } from '@angular/animations';

// Component transition animations
export const fadeRouteAnimation: AnimationTriggerMetadata =
  trigger('routeAnimation', [
    state('*',
      style({
        opacity: 1,
      }),
    ),
    transition(':enter', [
      style({
        opacity: 0,
      }),
      animate('0.3s ease-in'),
    ]),
    transition(':leave', [
      animate('0.5s ease-out', style({
        opacity: 0,
      })),
    ]),
  ]);
export const slideInLeftRouteAnimation: AnimationTriggerMetadata =
  trigger('routeAnimation', [
    state('*',
      style({
        opacity: 1,
        transform: 'translateX(0)',
      }),
    ),
    transition(':enter', [
      style({
        opacity: 0,
        transform: 'translateX(-100%)',
      }),
      animate('0.3s ease-in'),
    ]),
    transition(':leave', [
      animate('0.5s ease-out', style({
        opacity: 0,
        transform: 'translateX(100%)',
      })),
    ]),
  ]);
export const slideInDownRouteAnimation: AnimationTriggerMetadata =
  trigger('routeAnimation', [
    state('*',
      style({
        opacity: 1,
        transform: 'translateY(0)',
      }),
    ),
    transition(':enter', [
      style({
        opacity: 0,
        transform: 'translateY(-100%)',
      }),
      animate('0.3s ease-in'),
    ]),
    transition(':leave', [
      animate('0.5s ease-out', style({
        opacity: 0,
        transform: 'translateY(100%)',
      })),
    ]),
  ]);

  export const enterAnimation: AnimationTriggerMetadata = 
  trigger(
      'enterAnimation', [
        transition(':enter', [
          style({transform: 'translateX(100%)', opacity: 0}),
          animate('500ms', style({transform: 'translateX(0)', opacity: 1}))
        ]),
        transition(':leave', [
          style({transform: 'translateX(0)', opacity: 1}),
          animate('500ms', style({transform: 'translateX(100%)', opacity: 0}))
        ])
      ]
    );


export const fadeAnimation: AnimationTriggerMetadata = 
   trigger(
      'fadeAnimation', [
        transition(':enter', [
          style({opacity: 0}),
          animate('1000ms', style({opacity: 1}))
        ]),
        transition(':leave', [
          style({opacity: 1}),
          animate('1000ms', style({opacity: 0}))
        ])
      ]
    )