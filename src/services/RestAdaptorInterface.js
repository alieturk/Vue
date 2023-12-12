// export class RestAdaptorInterface /* <E extends Entity > */ {
//     resourcesUrl;
//     // the full url of the backend resource endpoint
//     copyConstructor;
//     // a reference to the copyConstructor of the entity: (E) => E
//
//     constructor(resourcesUrl, copyConstructor ) {
//         this.resourcesUrl = resourcesUrl;
//         this.copyConstructor = copyConstructor;
//
//     }
//     async asyncFindAll(){
//         return await this.copyConstructor(fetch(`$(this.resourcesUrl)`));
//     }
// //     // returns all entities that match the optional queryParams
// //     asyncFindById(id, queryParams = null)   /* :Promise<E> */                                    { return; }
// //     // retrieves the entity with given id, and applies optional queryParams
// //     asyncSave(entity, queryParams = null)   /* :Promise<E> */                                    { return; }
// //     // saves the given entity and applies optional queryParams
// //     asyncDelete(id, queryParams = null)     /* :Promise<void> */                                 { return; }
// //     // deletes the entity with given id and applies optional queryParams
// }
