import { Injectable } from '@angular/core';

import { 
    ExamineeAssessmentSession, 
    ExamineeAssessmentContainer, 
    ExamineeAssessmentHolder,
    CreateAssessmentContainerRequest,
    InstrumentAssessment,
    ExamineeAssessment,
    FormDetailSelectionItem
} from '../dto';

import { RaterFormTypeAssessmentConverter, FormTypeAssessmentConverter } from './utils';

@Injectable()
export class AssessmentSerDeserConverterService {  
    
    
    constructor() { }

    /**
     * 
     * @param assessmentSession
     *  {
            "examineeId":"sampleId6473643868478",
            "examineeAssessmentHolders":[
            {
                "name":"BASC-3-PRS",
                "examineeAssessmentDTOs":[
                    {
                    "version":null,
                    "id":null,
                    "formId":"BASC-3-PRS-Child",
                    "adminstrationDate":624765742656247,
                    "examinerId":"5942dcfd6034fbab245e47bc4",
                    "deliveryTypeId":"FORM_DELIVERY_TYPE.On.Screen.Administration",
                    "status":"Ready for Adminstration",
                    "rater":{
                        "firstName":"John",
                        "lastName":"Mark",
                        "email":"john.mark@gmail.com"
                    },
                    "subtestIds":[
                        "5942dcfd6034fbab245e47bc1",
                        "5942dcfd6034fbab245e47bc2",
                        "5942dcfd6034fbab245e47bc3"
                    ],
                    "bundleVariables":null
                    }
                ]
            }
        ]
     }
     */
    public createAssessmentRequest(assessmentSession: ExamineeAssessmentSession): CreateAssessmentContainerRequest {
        let request: CreateAssessmentContainerRequest;
        let payload: ExamineeAssessmentContainer = {
            examineeId: assessmentSession.examinee.id,
            examineeAssessmentHolders: []
        };
        request = {
            payload: payload
        }

        assessmentSession.instrumentAssessments.forEach((assessment: InstrumentAssessment) => {
            let form: FormDetailSelectionItem = assessment.instrument;
            let examineeAssessmentHolder: ExamineeAssessmentHolder = {
                groupId: form.id,
                examineeAssessments: []
            };

            if ( form.formType ) {
                let converter: FormTypeAssessmentConverter = this.getConverter(form.formType);
                let result = converter.convert(assessment);
                if ( !!result) {
                    examineeAssessmentHolder.examineeAssessments = examineeAssessmentHolder.examineeAssessments.concat(result);      
                }                          
            }

            payload.examineeAssessmentHolders.push(examineeAssessmentHolder);

        });

        return request;
    }

    private getConverter(formType: string) : FormTypeAssessmentConverter {
        return FORM_TYPE_ASSESSMENT_POPULATOR_MAP.find(it => it.type === formType).converter;        
    }
}

interface FormPopulator<T extends FormTypeAssessmentConverter> {
    type: string;
    converter: T ;
}
const FORM_TYPE_ASSESSMENT_POPULATOR_MAP: FormPopulator<FormTypeAssessmentConverter>[] = [
    {
        type: 'RATER',
        converter: new RaterFormTypeAssessmentConverter()
    }
]