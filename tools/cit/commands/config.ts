export interface CommandConfig {
    /* a name for the cit server */
    citHost?: string;

    /* actual url for cit server */
    citUrl: string;

    /* cit user name */
    citUser: string;

    /* cit password */
    citPassword: string;

    /* started by */
    startedBy?: string;

    /* metadata json destination */
    metadataDestDir? : string;

    /* cli extra arguments */
    extraArgs?: string[];
}