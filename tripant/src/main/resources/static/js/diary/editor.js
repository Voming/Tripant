//  ckeditor
//1. CKEditor 5 초기화: #editor 요소를 대상으로 CKEditor 5를 초기화합니다.
    
    
    
    CKEDITOR.ClassicEditor.create( document.querySelector( '#editor' ), {
       language: { ui: 'ko', content:'ko'},
        //2. 툴바 설정:
        toolbar: {
            items: [
                'aiCommands', 'aiAssistant', '|',
                'ckbox', 'uploadImage', '|',
                'exportPDF','exportWord', '|',
                'comment', 'trackChanges', 'revisionHistory', '|',
                'findAndReplace', 'selectAll', 'formatPainter', '|',
                'undo', 'redo',
                '-',
                'bold', 'italic', 'strikethrough', 'underline', 'removeFormat', '|',
                'bulletedList', 'numberedList', 'multiLevelList', 'todoList', '|',
                'outdent', 'indent', '|',
                'alignment', '|',
                '-',
                'heading', '|',
                'fontSize', 'fontFamily', 'fontColor', 'fontBackgroundColor', 'highlight', '|',
                'link', 'blockQuote', 'insertTable', 'mediaEmbed', 'codeBlock', 'htmlEmbed', 'tableOfContents', 'insertTemplate', '|',
                'specialCharacters', 'horizontalLine', 'pageBreak', '|',
                // 기타 툴바 항목들...
            ],
            shouldNotGroupWhenFull: true // shouldNotGroupWhenFull은 툴바가 가득 찼을 때 그룹화되지 않도록 합니다
        },
        //3. 목록 설정: 목록의 스타일, 시작 인덱스, 역순 설정을 허용합니다.
        list: {
            properties: {
                styles: true,
                startIndex: true,
                reversed: true
            }
        },
        // 4. 헤딩 설정:
        heading: {
            options: [
                { model: 'paragraph', title: 'Paragraph', class: 'ck-heading_paragraph' },
                { model: 'heading1', view: 'h1', title: 'Heading 1', class: 'ck-heading_heading1' },
                { model: 'heading2', view: 'h2', title: 'Heading 2', class: 'ck-heading_heading2' },
                { model: 'heading3', view: 'h3', title: 'Heading 3', class: 'ck-heading_heading3' },
                { model: 'heading4', view: 'h4', title: 'Heading 4', class: 'ck-heading_heading4' },
                { model: 'heading5', view: 'h5', title: 'Heading 5', class: 'ck-heading_heading5' },
                { model: 'heading6', view: 'h6', title: 'Heading 6', class: 'ck-heading_heading6' }
            ]
        },
        // 5. 폰트 설정: 폰트 패밀리와 폰트 크기 옵션 설정
        fontFamily: {
            options: [
                'default',
                'Arial, Helvetica, sans-serif',
                'Courier New, Courier, monospace',
                'Georgia, serif',
                'Lucida Sans Unicode, Lucida Grande, sans-serif',
                'Tahoma, Geneva, sans-serif',
                'Times New Roman, Times, serif',
                'Trebuchet MS, Helvetica, sans-serif',
                'Verdana, Geneva, sans-serif'
            ],
            supportAllValues: true
        },
        // https://ckeditor.com/docs/ckeditor5/latest/features/font.html#configuring-the-font-size-feature
        fontSize: {
            options: [ 10, 12, 14, 'default', 18, 20, 22 ],
            supportAllValues: true
        },
        //06. HTML 임베드 설정 :ㅣHTML 임베드 기능을 사용하며, 미리보기를 표시합니다.
        htmlEmbed: {
            showPreviews: true
        },
        // 7.언급(Mention) 설정 :'@' 문자를 사용한 언급 기능을 설정합니다.
        mention: {
            feeds: [
                {
                    marker: '@',
                    feed: [
                        '@apple', '@bears', '@brownie', '@cake', '@cake', '@candy', '@canes', '@chocolate', '@cookie', '@cotton', '@cream',
                        '@cupcake', '@danish', '@donut', '@dragée', '@fruitcake', '@gingerbread', '@gummi', '@ice', '@jelly-o',
                        '@liquorice', '@macaroon', '@marzipan', '@oat', '@pie', '@plum', '@pudding', '@sesame', '@snaps', '@soufflé',
                        '@sugar', '@sweet', '@topping', '@wafer'
                    ],
                    minimumCharacters: 1
                }
            ]
        },
        //8. 템플릿 설정:
        template: {
            definitions: [
                {
                    title: 'The title of the template',
                    description: 'A longer description of the template',
                    data: '<p>Data inserted into the content</p>'
                },
                {
                    title: 'Annual financial report',
                    description: 'A report that spells out the company\'s financial condition.',
                    data: `<figure class="table">
						<table style="border:2px solid hsl(0, 0%, 0%);">
							<thead>
								<tr>
									<th style="text-align:center;" rowspan="2">Metric name</th>
									<th style="text-align:center;" colspan="4">Year</th>
								</tr>
								<tr>
									<th style="background-color:hsl(90, 75%, 60%);text-align:center;">2019</th>
									<th style="background-color:hsl(90, 75%, 60%);text-align:center;">2020</th>
									<th style="background-color:hsl(90, 75%, 60%);text-align:center;">2021</th>
									<th style="background-color:hsl(90, 75%, 60%);text-align:center;">2022</th>
								</tr>
							</thead>
							<tbody>
								<tr>
									<th><strong>Revenue</strong></th>
									<td>$100,000.00</td>
									<td>$120,000.00</td>
									<td>$130,000.00</td>
									<td>$180,000.00</td>
								</tr>
								<tr>
									<th style="background-color:hsl(0, 0%, 90%);"><strong>Operating expenses</strong></th>
									<td>&nbsp;</td>
									<td>&nbsp;</td>
									<td>&nbsp;</td>
									<td>&nbsp;</td>
								</tr>
								<tr>
									<th><strong>Interest</strong></th>
									<td>&nbsp;</td>
									<td>&nbsp;</td>
									<td>&nbsp;</td>
									<td>&nbsp;</td>
								</tr>
								<tr>
									<th style="background-color:hsl(0, 0%, 90%);"><strong>Net profit</strong></th>
									<td>&nbsp;</td>
									<td>&nbsp;</td>
									<td>&nbsp;</td>
									<td>&nbsp;</td>
								</tr>
							</tbody>
						</table>
					</figure>`
                },
            ]
        },
        // https://ckeditor.com/docs/ckeditor5/latest/features/editor-placeholder.html#using-the-editor-configuration
        placeholder: '행복했던 여행을 기록해보세요',
        // 9. 클라우드 서비스 설정
        cloudServices: {
            // Be careful - do not use the development token endpoint on production systems!
            tokenUrl: 'https://110380.cke-cs.com/token/dev/ALTCWJbpBbcPjffRdzRes4WagIzLqxcv7h9J?limit=10',
            webSocketUrl: 'wss://110380.cke-cs.com/ws',
            uploadUrl: 'https://110380.cke-cs.com/easyimage/upload/'
        },
        //10. 실시간 협업 설정
        collaboration: {
            // Modify the channelId to simulate editing different documents
            // https://ckeditor.com/docs/ckeditor5/latest/features/collaboration/real-time-collaboration/real-time-collaboration-integration.html#the-channelid-configuration-property
            channelId: 'document-id-8'
        },
        // 11. 사이드바 설정: 사이드바, 문서 윤곽, 사용자 리스트 등의 컨테이너를 설정합니다.
        /*sidebar: {
            container: document.querySelector( '#sidebar' )
        },*/
        documentOutline: {
            container: document.querySelector( '#outline'),
            showEmptyHeadings: true
        },
        // https://ckeditor.com/docs/ckeditor5/latest/features/collaboration/real-time-collaboration/users-in-real-time-collaboration.html#users-presence-list
       /* presenceList: {
            container: document.querySelector( '#presence-list-container' )
        },*/
        //12.주석 및 수정 내역 설정: 주석 및 수정 내역 기능을 설정합니다.
        comments: {
            editorConfig: {
                extraPlugins: CKEDITOR.ClassicEditor.builtinPlugins.filter( plugin => {
                    // Use e.g. Ctrl+B in the comments editor to bold text.
                    return [ 'Bold', 'Italic', 'Underline', 'List', 'Autoformat', 'Mention' ].includes( plugin.pluginName );
                } ),
                // Combine mentions + Webhooks to notify users about new comments
                // https://ckeditor.com/docs/cs/latest/guides/webhooks/events.html
                mention: {
                    feeds: [
                        {
                            marker: '@',
                            feed: [
                                '@Baby Doe', '@Joe Doe', '@Jane Doe', '@Jane Roe', '@Richard Roe'
                            ],
                            minimumCharacters: 1
                        }
                    ]
                },
            }
        },
        // Do not include revision history configuration if you do not want to integrate it.
        // Remember to remove the 'revisionHistory' button from the toolbar in such a case.
       /* revisionHistory: {
            editorContainer: document.querySelector( '#editor-container' ),
            viewerContainer: document.querySelector( '#revision-viewer-container' ),
            viewerEditorElement: document.querySelector( '#revision-viewer-editor' ),
            viewerSidebarContainer: document.querySelector( '#revision-viewer-sidebar' ),
        },*/
        // https://ckeditor.com/docs/ckeditor5/latest/features/images/image-upload/ckbox.html
        ckbox: {
            // Be careful - do not use the development token endpoint on production systems!
            tokenUrl: 'https://110380.cke-cs.com/token/dev/ALTCWJbpBbcPjffRdzRes4WagIzLqxcv7h9J?limit=10'
        },
        ai: {
            // AI Assistant feature configuration.
            // https://ckeditor.com/docs/ckeditor5/latest/features/ai-assistant.html
            aiAssistant: {
                contentAreaCssClass: "formatted"
            },
            // Configure one of the supported AI integration: OpenAI, Azure OpenAI, Amazon Bedrock
            // https://ckeditor.com/docs/ckeditor5/latest/features/ai-assistant/ai-assistant-integration.html#integration
            openAI: {
                // apiUrl: 'https://url.to.your.application/ai'
            }
        },
        style: {
            definitions: [
                {
                    name: 'Article category',
                    element: 'h3',
                    classes: [ 'category' ]
                },
                {
                    name: 'Info box',
                    element: 'p',
                    classes: [ 'info-box' ]
                },
            ]
        },
        // License key is required only by the Pagination plugin and non-realtime Comments/Track changes.
        licenseKey: 'Umw0V1kyQ3hWOFRoNlA2cGpJV0ZCQmZIbHZBc2FzcUJBWlV4cndqa0dJaFhROGdSTHFNa3FVd1pDeFlTQnc9PS1NakF5TkRBM01EUT0=',
        removePlugins: [
            // Before enabling Pagination plugin, make sure to provide proper configuration and add relevant buttons to the toolbar
            // https://ckeditor.com/docs/ckeditor5/latest/features/pagination/pagination.html
            'Pagination',
            // Intentionally disabled, file uploads are handled by CKBox
            'Base64UploadAdapter',
            // Intentionally disabled, file uploads are handled by CKBox
            'CKFinder',
            // Intentionally disabled, file uploads are handled by CKBox
            'EasyImage',
            // Requires additional license key
            'WProofreader',
            // Incompatible with real-time collaboration
            'SourceEditing',
            // Careful, with the Mathtype plugin CKEditor will not load when loading this sample
            // from a local file system (file://) - load this site via HTTP server if you enable MathType
            'MathType'
            // If you would like to adjust enabled collaboration features:
            // 'RealTimeCollaborativeComments',
            // 'RealTimeCollaborativeTrackChanges',
            // 'RealTimeCollaborativeRevisionHistory',
            // 'PresenceList',
            // 'Comments',
            // 'TrackChanges',
            // 'TrackChangesData',
            // 'RevisionHistory',
        ]
    } )
        .then( editor => {
            window.editor = editor;

            // Example implementation to switch between different types of annotations according to the window size.
            // https://ckeditor.com/docs/ckeditor5/latest/features/collaboration/annotations/annotations-display-mode.html
            const annotationsUIs = editor.plugins.get( 'AnnotationsUIs' );
            const sidebarElement = document.querySelector( '.sidebar' );
            let currentWidth;

            function refreshDisplayMode() {
                // Check the window width to avoid the UI switching when the mobile keyboard shows up.
                if ( window.innerWidth === currentWidth ) {
                    return;
                }
                currentWidth = window.innerWidth;

                if ( currentWidth < 1000 ) {
                    sidebarElement.classList.remove( 'narrow' );
                    sidebarElement.classList.add( 'hidden' );
                    annotationsUIs.switchTo( 'inline' );
                }
                else if ( currentWidth < 1300 ) {
                    sidebarElement.classList.remove( 'hidden' );
                    sidebarElement.classList.add( 'narrow' );
                    annotationsUIs.switchTo( 'narrowSidebar' );
                }
                else {
                    sidebarElement.classList.remove( 'hidden', 'narrow' );
                    annotationsUIs.switchTo( 'wideSidebar' );
                }
            }

         /*   editor.ui.view.listenTo( window, 'resize', refreshDisplayMode );
            refreshDisplayMode();*/

            return editor;
        } )
        .catch( error => {
            console.error(error );
        } );