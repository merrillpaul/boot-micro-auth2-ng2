export interface FormDetailSelectionItem {
    id:string;
    acronym?: string;
    description?: string;
    assessmentName?: string;
    formType: string;
    formDeliveryTypes?: string[];
    displayExaminer?: boolean;
    displayTeacher?: boolean;
    version?: number;
}